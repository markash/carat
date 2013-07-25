package za.co.yellowfire.carat.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.batch.runtime.BatchStatus;
import java.util.Date;
import java.util.Properties;

@Slf4j @AllArgsConstructor
public class BatchExecution {
    @Getter
    private long instanceId;
    /**
     * Get unique id for this JobExecution.
     * @return execution id
     */
    @Getter
    private long executionId;
    /**
     * Get job name.
     * @return value of 'id' attribute from <job>
     */
    @Getter
    private String jobName;
    /**
     * Get batch status of this execution.
     * @return batch status value.
     */
    @Getter
    private BatchStatus status;
    /**
     * Get time execution entered STARTED status.
     * @return date (time)
     */
    @Getter
    private Date startTime;
    /**
     * Get time execution entered end status: COMPLETED, STOPPED, FAILED
     * @return date (time)
     */
    @Getter
    private Date endTime;
    /**
     * Get execution exit status.
     * @return exit status.
     */
    @Getter
    private String exitStatus;
    /**
     * Get time execution was created.
     * @return date (time)
     */
    @Getter
    private Date createTime;
    /**
     * Get time execution was last updated updated.
     * @return date (time)
     */
    @Getter
    private Date lastUpdatedTime;
    /**
     * Get job parameters for this execution.
     * @return job parameters
     */
    @Getter
    private Properties jobParameters;
}
