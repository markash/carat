package za.co.yellowfire.carat.batch;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import java.util.*;

@Slf4j
public class Batch {
    @Getter
    private String name;
    private Properties props = new Properties();
    @Getter @Setter
    private JobOperator operator;

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
