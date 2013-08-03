package za.co.yellowfire.carat.web;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Faces;

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
