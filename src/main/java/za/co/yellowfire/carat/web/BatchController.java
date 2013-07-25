package za.co.yellowfire.carat.web;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.yellowfire.carat.batch.Batch;
import za.co.yellowfire.carat.batch.JobException;
import za.co.yellowfire.carat.batch.JobRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named @ViewScoped @Slf4j
public class BatchController {

    @Getter @Setter
    private JobRepository repository;

    @PostConstruct
    public void init() {
        repository = new JobRepository();
        try {
            repository.add(new Batch("alwaysFail1").property("sleepTime", "5"));
        } catch (JobException e) {
            log.error("Unable to add job to repository", e);
        }
    }

    public List<Batch> getBatches() {
        return repository.getBatches();
    }

    public String onSubmit() {
        return "success";
    }
}
