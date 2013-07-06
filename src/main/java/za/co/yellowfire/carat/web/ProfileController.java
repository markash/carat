package za.co.yellowfire.carat.web;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Messages;
import za.co.yellowfire.carat.db.DataAccessException;
import za.co.yellowfire.carat.db.Role;
import za.co.yellowfire.carat.db.User;
import za.co.yellowfire.carat.db.UserDao;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named @ViewScoped @Slf4j
public class ProfileController {

    @Getter @Setter
    private String id;

    private User user;

    @Getter
    private List<Role> roles;

    @Inject
    private UserDao userDao;

    public User getUser() {
        if (this.user == null) {
            retrieveUser(id);
        }
        return user;
    }

    private void retrieveUser(String userName) {
        System.out.println("userName = " + userName);
        try {
            this.user = userDao.getUser(userName);
            this.roles = userDao.getRoles();
        } catch (DataAccessException e) {
            Messages.addGlobalError("User retrieval failed: {0}", e.getMessage());
            log.error("User retrieval failed: {}", e.getMessage());
        }
    }

    public String onUpdate() {
        try {
            user = userDao.update(user);
            Messages.addGlobalInfo("Registration succeeded, new user ID is: {0}", user.getId());
            return "update";
        } catch (DataAccessException e) {
            Messages.addGlobalError("Profile update failed: {0}", e.getMessage());
            log.error("Profile update failed: {}", e.getMessage());
            return "error";
        }
    }

    public String onCancel() {
        return "/index?faces-redirect=true";
    }
}