package za.co.yellowfire.carat.db;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jdbi.InstrumentedTimingCollector;
import org.skife.jdbi.v2.DBI;
import za.co.yellowfire.carat.metrics.Metrics;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class JdbiProducer {
    @Inject
    @Metrics
    private MetricRegistry metricRegistry;

    @Produces
    private ItemDao produceItemDao() {
        DBI dbi = new DBI("jdbc:hsqldb:mem:sample;sql.enforce_strict_size=true", "sa", "");
        dbi.setTimingCollector(new InstrumentedTimingCollector(metricRegistry));
        return dbi.open(ItemDao.class);
    }
}
