package za.co.yellowfire.carat.batch;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JobRepository {
    private Map<String, Batch> batches;
    private JobOperator operator = BatchRuntime.getJobOperator();

    public JobRepository() {
        this.batches = new HashMap<>();
    }

    public JobRepository add(@NonNull Batch batch) throws JobException {
        if (batch == null) { throw new JobException ("The job is not set"); }
        if (batch.getName() == null) { throw new JobException ("The job name is not set"); }

        this.batches.put(batch.getName(), batch.operator(operator));
        return this;
    }

    public List<Batch> getBatches() {
        return new ArrayList<>(batches.values());
    }
}
