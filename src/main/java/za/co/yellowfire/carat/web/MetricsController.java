package za.co.yellowfire.carat.web;

import com.codahale.metrics.JmxReporter;
import za.co.yellowfire.carat.metrics.Metrics;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

//@Named
//@Startup
//@Singleton
public class MetricsController implements Serializable {

    //@Inject @Metrics
    private JmxReporter reporter;

    //@PostConstruct
    public void startUp() {
        reporter.start();
    }
}
