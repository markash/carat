package za.co.yellowfire.carat.web;

import za.co.yellowfire.carat.db.Item;
import za.co.yellowfire.carat.db.ItemDao;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class IndexController implements Serializable {

    @Inject
    private ItemDao itemDao;

//    @Inject @Metrics
//    private MetricRegistry metricRegistry;

    private List<Item> items;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        if (this.items == null) {
            this.items = itemDao.getItems();
        }
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void onSubmit() {
        System.out.println("response");
        //metricRegistry.counter("index.response").inc();
        //return "index";
    }

//    protected BeanManager getBeanManager() {
//        System.out.println("getBeanManager()");
//        BeanManager bm = getBeanManagerInServletContext();
//        if (bm == null) {
//           bm = getBeanManagerInJndi();
//        }
//        return bm;
//    }
//
//    protected BeanManager getBeanManagerInServletContext() {
//        System.out.println("getBeanManagerInServletContext()");
//        return (BeanManager)
//                ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
//                        .getAttribute("javax.enterprise.inject.spi.BeanManager");
//    }
//
//    protected BeanManager getBeanManagerInJndi() {
//        System.out.println("getBeanManagerInJndi()");
//        try{
//            return (BeanManager) new InitialContext().lookup("java:comp/env/BeanManager");
//        } catch (NamingException e) {
//            return null;
//        }
//    }

//    protected MetricRegistry getMetricRegistry() {
//        BeanManager bm = getBeanManager();
//        System.out.println("bm = " + bm);
//        Set<Bean<?>> beans = bm.getBeans(MetricRegistry.class, new AnnotationLiteral<Metrics>() {});
//        Bean<?> bean = beans.iterator().next();
//        CreationalContext<?> ctx = bm.createCreationalContext(bean);
//        return (MetricRegistry) bm.getReference(bean, MetricRegistry.class, ctx);
//    }

//    protected ItemDao getItemDao() {
//        System.out.println("getItems()");
//        BeanManager bm = getBeanManager();
//        System.out.println("bm = " + bm);
//        Set<Bean<?>> beans = bm.getBeans(ItemDao.class);
//        Bean<?> bean = beans.iterator().next();
//        CreationalContext<?> ctx = bm.createCreationalContext(bean);
//        return (ItemDao) bm.getReference(bean, ItemDao.class, ctx);
//    }
}
