package za.co.yellowfire.carat.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import za.co.yellowfire.carat.db.DataAccessException;
import za.co.yellowfire.carat.db.User;
import za.co.yellowfire.carat.db.UserDao;
import za.co.yellowfire.carat.security.annotations.ShiroSecured;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named @ViewScoped @Slf4j @ShiroSecured
public class UsersController {

    @Inject
    private UserDao userDao;

    private transient MemoryPageableDataModel<User> users;

    @RequiresRoles("ADMIN")
    public MemoryPageableDataModel<User> getUsers() throws DataAccessException {
        if (this.users == null) {
            this.users = new MemoryPageableDataModel<>(userDao);
        }
        return users;
    }
}
