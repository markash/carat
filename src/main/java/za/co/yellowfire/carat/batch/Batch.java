package za.co.yellowfire.carat.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.batch.runtime.BatchStatus;
import javax.faces.event.ActionEvent;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Batch {
    @Getter
    private String name;
    private Properties props = new Properties();
    @Getter @Setter
    private JobOperator operator;
    private BatchExecution lastExecution;
    
    public Batch(@NonNull String name) {
        this.name = name;
    }

    public Batch(@NonNull String name, @NonNull JobOperator operator) {
        this.name = name;
        this.operator = operator;
    }

    public void setProperty(String name, String value) {
        props.setProperty(name, value);
    }

    public Properties getProperties() {
        return new Properties(this.props);
    }

    public String getPropertiesAsCsv() {
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            if (builder.length() > 0) { builder.append(","); }
            builder.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return builder.toString();
    }

    public Batch property(String name, String value) {
        this.setProperty(name, value);
        return this;
    }

    public Batch operator(JobOperator operator) {
        this.setOperator(operator);
        return this;
    }

    public List<JobInstance> getInstances(int start, int count) {
        return operator.getJobInstances(this.name, start, count);
    }

    public boolean isStartable() {
	final BatchExecution le = getLastExecution();	
	boolean result = false;
	if (le != null) {
		switch(le.getStatus()) {
            case STARTING: result = true; break;
		    case COMPLETED: result = true; break;
		    default: result = false;
		}
	} else {
		result = true;
	}
	return result;
    }
    
    public boolean isStoppable() {
	final BatchExecution le = getLastExecution();	
	boolean result = false;
	if (le != null) {
		switch(le.getStatus()) {
		case STARTED: result = true; break;
		default: result = false;
		}
	} else {
		result = true;
	}
	return result;
    }
    
    public boolean isResumable() {
    	final BatchExecution le = getLastExecution();
	boolean result = false;
    	if (le != null && le.getStatus() != null) {
		switch(le.getStatus()) {
		case STOPPING: result = true; break; 
		case STOPPED: result = true; break;
		default: result = false;
		}
    	}
    	return result;
    }
    
    public void onStart(ActionEvent event) {
        final long currentExecutionId = operator.start(this.name, this.props);
        JobExecution execution = operator.getJobExecution(currentExecutionId);
        this.lastExecution = new BatchExecution(
                0,
                execution.getExecutionId(),
                execution.getJobName(),
                execution.getBatchStatus(),
                execution.getStartTime(),
                execution.getEndTime(),
                execution.getExitStatus(),
                execution.getCreateTime(),
                execution.getLastUpdatedTime(),
                execution.getJobParameters());
        
    }
    
    public void onStop(ActionEvent event) {
	final BatchExecution le = getLastExecution();	
	if (le != null) {
	final long currentExecutionId = le.getExecutionId();
	/* Determine if the batch is stoppable */
	/* Stop the batch */
        operator.stop(currentExecutionId);
	/* Refresh the batch execution status */        
        JobExecution execution = operator.getJobExecution(currentExecutionId);
        this.lastExecution = new BatchExecution(
                0,
                execution.getExecutionId(),
                execution.getJobName(),
                execution.getBatchStatus(),
                execution.getStartTime(),
                execution.getEndTime(),
                execution.getExitStatus(),
                execution.getCreateTime(),
                execution.getLastUpdatedTime(),
                execution.getJobParameters());
	}
    }
    
    public void onResume(ActionEvent event) {
	final BatchExecution le = getLastExecution();	
	if (le != null) {
	final long currentExecutionId = le.getExecutionId();
	/* Determine if the batch is resumeable */
	/* Resume the batch */
        operator.restart(currentExecutionId, this.props);  
	/* Refresh the batch execution status */        
        JobExecution execution = operator.getJobExecution(currentExecutionId);
        this.lastExecution = new BatchExecution(
                0,
                execution.getExecutionId(),
                execution.getJobName(),
                execution.getBatchStatus(),
                execution.getStartTime(),
                execution.getEndTime(),
                execution.getExitStatus(),
                execution.getCreateTime(),
                execution.getLastUpdatedTime(),
                execution.getJobParameters());
	}
    }
    
    public BatchExecution getLastExecution() {
    	try {
            /* Job Instances are ordered in desc order so get the first item will be the last instance */
            List<JobInstance> instances = operator.getJobInstances(this.name, 0, 1);
            if (instances.size() > 0) {
                List<JobExecution> executions = operator.getJobExecutions(instances.get(0));
                /* Job Executions are ordered in desc order so get the first item will be the last execution */
                if (executions.size() > 0) {
                    JobExecution execution = executions.get(0);
                    return new BatchExecution(
                            instances.get(0).getInstanceId(),
                            execution.getExecutionId(),
                            execution.getJobName(),
                            execution.getBatchStatus(),
                            execution.getStartTime(),
                            execution.getEndTime(),
                            execution.getExitStatus(),
                            execution.getCreateTime(),
                            execution.getLastUpdatedTime(),
                            execution.getJobParameters());
                }
            }
    	} catch (NoSuchJobException e) {
    		/* NoSuchJobException is thrown when the job could not be loaded and when there are no executions ;-S */
    		log.warn("No job instances for name {}", this.name);
    	}
        return new BatchExecution();
    }

    public List<BatchExecution> getLastExecutions(int count) {
        int total = operator.getJobInstanceCount(this.name);
        int start = count > total ? 0 : total - count;

        List<BatchExecution> results = new ArrayList<>();
        List<JobInstance> instances = getInstances(start, count);
        for (JobInstance instance : instances) {
            List<JobExecution> executions = operator.getJobExecutions(instance);
            for (JobExecution execution : executions) {

                results.add(new BatchExecution(
                        instance.getInstanceId(),
                        execution.getExecutionId(),
                        execution.getJobName(),
                        execution.getBatchStatus(),
                        execution.getStartTime(),
                        execution.getEndTime(),
                        execution.getExitStatus(),
                        execution.getCreateTime(),
                        execution.getLastUpdatedTime(),
                        execution.getJobParameters()));
            }
        }

        Collections.sort(results, new Comparator<BatchExecution>() {
            @Override
            public int compare(BatchExecution o1, BatchExecution o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        return results;
    }
}
