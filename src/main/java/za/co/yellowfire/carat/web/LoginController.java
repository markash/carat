package za.co.yellowfire.carat.web;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class LoginController {
    public static final String HOME_URL = "index.jsf";

    private String username;
    private String password;
    private boolean remember;

    public void submit() throws IOException {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
        }
        catch (AuthenticationException e) {
            Messages.addGlobalError("Unknown user, please try again");
            e.printStackTrace(); // TODO: logger.
        }
    }
}
