package za.co.yellowfire.carat.web;

import java.io.Serializable;

import com.codahale.metrics.JmxReporter;

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
