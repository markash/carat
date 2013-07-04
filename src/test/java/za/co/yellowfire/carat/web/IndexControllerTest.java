package za.co.yellowfire.carat.web;

import za.co.yellowfire.carat.db.ItemDao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class IndexControllerTest extends Arquillian {

    @Inject
    private IndexController controller;

    @Deployment
    @SuppressWarnings("unused")
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
