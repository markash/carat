package za.co.yellowfire.carat.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilterContextListener;

public class MetricsInstrumentedFilterContextListener extends InstrumentedFilterContextListener {
    public static final MetricRegistry REGISTRY = MetricsAdminServletContextListener.metricRegistry;

    @Override
    protected MetricRegistry getMetricRegistry() {
        return REGISTRY;
    }
}
