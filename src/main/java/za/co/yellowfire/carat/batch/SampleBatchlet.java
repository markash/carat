package za.co.yellowfire.carat.batch;

import javax.batch.api.AbstractBatchlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SampleBatchlet extends AbstractBatchlet {
    public String process() {
        log.info("SampleBatchlet.process");
        try { Thread.sleep(5000); } catch (InterruptedException e) {}
        return "COMPLETED"; // exit status
    }

}