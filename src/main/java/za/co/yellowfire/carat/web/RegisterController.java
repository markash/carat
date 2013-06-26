package za.co.yellowfire.carat.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Messages;
import za.co.yellowfire.carat.db.DataAccessException;
import za.co.yellowfire.carat.db.Role;
import za.co.yellowfire.carat.db.User;
import za.co.yellowfire.carat.db.UserDao;

import java.util.List;

@Named
@ViewScoped @Slf4j
public class RegisterController {

    private User user;

    @Getter
    private List<Role> roles;

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void init() throws DataAccessException {
        this.user = new User();
        this.roles = userDao.getRoles();
    }

    public void submit() {
        try {
            user = userDao.create(user);
            Messages.addGlobalInfo("Registration succeeded, new user ID is: {0}", user.getId());
        } catch (DataAccessException e) {
            Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            log.error("Registration failed: {}", e.getMessage());
        }
    }

    public User getUser() {
        return user;
    }

}