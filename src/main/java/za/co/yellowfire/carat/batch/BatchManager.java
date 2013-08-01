package za.co.yellowfire.carat.batch;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named @ApplicationScoped @Slf4j
public class BatchManager {

    @Getter @Setter
    private BatchRepository repository;

    @PostConstruct
    public void init() {
        repository = new BatchRepository();
        try {
            repository.add(new Batch("runtimejunit.alwaysFails1").property("sleepTime", "5"));
        } catch (JobException e) {
            BatchManager.log.error("Unable to add job to repository", e);
        }
    }

    public List<Batch> getBatches() {
        return repository.retrieve();
    }
}
