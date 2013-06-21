package za.co.yellowfire.carat.web;

import com.codahale.metrics.JmxReporter;
import za.co.yellowfire.carat.metrics.Metrics;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Startup
@Singleton
public class MetricsController {

    @Inject @Metrics
    private JmxReporter reporter;

    @PostConstruct
    public void startUp() {
        reporter.start();
    }
}
