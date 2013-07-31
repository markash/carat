package za.co.yellowfire.carat.web;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import za.co.yellowfire.carat.db.ItemDao;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class IndexControllerTest  {

    @Inject
    private IndexController controller;

    @SuppressWarnings("unused") @Deployment @OverProtocol("Servlet 3.0")
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(IndexController.class)
                .addClass(ItemDao.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void should_create_greeting() {
        assertNotNull(controller);

        controller.setName("Mark");
        assertEquals(controller.getName(), "Mark");
    }
}
