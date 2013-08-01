package za.co.yellowfire.carat.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import za.co.yellowfire.carat.batch.Batch;
import za.co.yellowfire.carat.batch.BatchManager;
import za.co.yellowfire.carat.db.DataAccessException;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Slf4j @ViewScoped @Named
public class BatchController {

    @Inject
    private BatchManager manager;
    private transient MemoryPageableDataModel<Batch> batches;

    @RequiresRoles("ADMIN")
    public MemoryPageableDataModel<Batch> getBatches() throws DataAccessException {
        if (this.batches == null) {
            this.batches = new MemoryPageableDataModel<>(manager.getRepository());
        }
        return batches;
    }
}
