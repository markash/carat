package za.co.yellowfire.carat.web;

import lombok.extern.slf4j.Slf4j;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Properties;

@Named @ViewScoped @Slf4j
public class BatchController {

    public String onSubmit() {

        JobOperator operator = BatchRuntime.getJobOperator();

        Properties props = new Properties();
        props.setProperty("sleepTime", "5");
        operator.start("alwaysFail1", props);

        for (String jn : operator.getJobNames()) {
            System.out.println("JN: " + jn);
            List<JobInstance> exe = operator.getJobInstances(jn, 0, 200);
            if (exe != null) {
                for (JobInstance ji : exe) {
                    System.out.println("JI: " + ji.getInstanceId());
                    for (JobExecution je : operator.getJobExecutions(ji)) {
                        System.out.println("JE: " + je.getExecutionId());
                        if (je.getJobName() != null) {
                            System.out.println("SKSK: ");
                        } else {
                            throw new RuntimeException("saw null for je: " + je.getExecutionId()
                                    + ", ji: " + ji.getInstanceId() + ", jn= " + jn);
                        }
                    }
                }
            }
        }

        return "success";
    }
}
