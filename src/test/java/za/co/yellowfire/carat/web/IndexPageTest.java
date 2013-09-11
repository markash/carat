package za.co.yellowfire.carat.web;

import com.thoughtworks.selenium.DefaultSelenium;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import za.co.yellowfire.carat.db.Item;
import za.co.yellowfire.carat.db.ItemDao;
import za.co.yellowfire.carat.db.Role;
import za.co.yellowfire.carat.db.User;

import java.io.File;
import java.net.URL;

@RunWith(Arquillian.class)
public class IndexPageTest {
    private static final String WEB_SRC = "src/main/webapp";
    private static final String WEB_TEMPLATES_SRC = WEB_SRC + "/templates";

    @Drone DefaultSelenium browser;
    @ArquillianResource URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        File[] libraries = Maven.resolver()
                .resolve(
                        "org.webjars:bootstrap:3.0.0-rc.2",
                        "org.webjars:jquery:1.10.2",
                        "za.co.yellowfire:carat-db:0.1.3",
                        "za.co.yellowfire:carat-web:0.1.3")
                .withTransitivity().asFile();

        WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(libraries)
                .addClasses(
                        IndexController.class)
                .addAsWebResource(new File(WEB_SRC, "index.html"))
                .addAsWebResource(new File(WEB_SRC, "index.xhtml"))
                .addAsWebResource(new File(WEB_SRC, "login.xhtml"))
                .addAsWebResource(new File(WEB_SRC, "profile.xhtml"))
                .addAsWebResource(new File(WEB_SRC, "register.xhtml"))
                .addAsWebResource(new File(WEB_TEMPLATES_SRC, "main.xhtml"), "templates/main.xhtml")
                .addAsWebResource(new File(WEB_TEMPLATES_SRC, "main-html4.xhtml"), "templates/main-html4.xhtml")
                .addAsWebInfResource(new File("src/test/resources", "in_container_web.xml"), "web.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");

        System.out.println("archive = " + archive.toString(true));
        return archive;
    }

    @Test
    public void should_login_successfully() {
        browser.open(deploymentUrl + "login.jsf");

        //browser.type("id=form:username", "demo");
        //browser.type("id=loginForm:password", "demo");
        //browser.click("id=loginForm:login");

        browser.waitForPageToLoad("15000");

        browser.captureScreenshot("index.png");
        //Assert.assertTrue("User should be logged in!",
        //        browser.isElementPresent("xpath=//li[contains(text(), 'Welcome')]"));
        //Assert.assertTrue("Username should be shown!",
        //        browser.isElementPresent("xpath=//p[contains(text(), 'You are signed in as demo.')]"));
    }
}