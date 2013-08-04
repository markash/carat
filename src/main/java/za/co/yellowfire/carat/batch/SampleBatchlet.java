package za.co.yellowfire.carat.batch;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SampleBatchlet extends AbstractBatchlet {
	
	@Inject JobContext jobContext;
	@Inject StepContext stepContext;
	
    public String process() {
        log.info("SampleBatchlet.process begin");
        try { Thread.sleep(60000); } catch (InterruptedException e) {}
	log.info("SampleBatchlet.process end");
        return "COMPLETED"; // exit status
    }

}
