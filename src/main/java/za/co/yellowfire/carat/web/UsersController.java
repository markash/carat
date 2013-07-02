package za.co.yellowfire.carat.web;

import lombok.extern.slf4j.Slf4j;
import za.co.yellowfire.carat.db.DataAccessException;
import za.co.yellowfire.carat.db.User;
import za.co.yellowfire.carat.db.UserDao;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named @ViewScoped @Slf4j
public class UsersController {

    @Inject
    private UserDao userDao;

    private transient MemoryPageableDataModel<User> users;

    public MemoryPageableDataModel<User> getUsers() throws DataAccessException {
        if (this.users == null) {
            this.users = new MemoryPageableDataModel<>(userDao);
        }
        return users;
    }
}
