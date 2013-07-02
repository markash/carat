package za.co.yellowfire.carat.web;

import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Faces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.IOException;

@Named @RequestScoped
public class LogoutController {
    public static final String HOME_URL = "login.jsf";

    public void submit() throws IOException {
        SecurityUtils.getSubject().isRemembered();

        SecurityUtils.getSubject().logout();

        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }
}
