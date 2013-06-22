package za.co.yellowfire.carat.web;

import com.codahale.metrics.MetricRegistry;
import za.co.yellowfire.carat.db.Item;
import za.co.yellowfire.carat.db.ItemDao;
import za.co.yellowfire.carat.metrics.Metrics;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class IndexController implements Serializable {

    @Inject @Metrics
    private MetricRegistry metricRegistry;

    private List<Item> items;
    private String name;

    @Inject @PostConstruct
    public void init(ItemDao dao) {
        items = dao.getItems();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String response() {
        metricRegistry.counter("index.response").inc();
        return "index";
    }
}
