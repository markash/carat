package za.co.yellowfire.carat.batch;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.AbstractBatchlet;

@Slf4j
public final class SampleBatchlet extends AbstractBatchlet {
    public String process() {
        log.info("SampleBatchlet.process");
        return "COMPLETED"; // exit status
    }

}