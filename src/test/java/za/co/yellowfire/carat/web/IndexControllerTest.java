package za.co.yellowfire.carat.web;

import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.com.it_crowd.arquillian.mock_contexts.ViewScopeRequired;
import za.co.yellowfire.carat.db.ItemDao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import javax.inject.Inject;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class IndexControllerTest  {

    @Inject
    private IndexController controller;

    @Deployment @OverProtocol("Servlet 3.0")
    @SuppressWarnings("unused")
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(IndexController.class)
                .addClass(ItemDao.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ViewScopeRequired @Test
    public void should_create_greeting() {
        assertNotNull(controller);

        controller.setName("Mark");
        System.out.println("controller = " + controller.getName());
        assertEquals(controller.getName(), "Mark");
    }
}
