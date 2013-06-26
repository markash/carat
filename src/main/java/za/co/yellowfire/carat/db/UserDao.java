package za.co.yellowfire.carat.db;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import za.co.yellowfire.carat.db.postgres.tables.AppUser;
import za.co.yellowfire.carat.db.postgres.tables.AppUserRole;

import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
public class UserDao implements Serializable {

    private DataSource getDataSource() throws NamingException {
        return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/carat");
    }

    public List<User> getUsers() throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            return create.select().from(AppUser.APP_USER)
                            .fetch(new RecordMapper<Record, User>() {
                                @Override
                                public User map(Record record) {
                                    List<Role> roles = new ArrayList<Role>(0);
                                    try {
                                        roles = getRoles(record.getValue(AppUser.APP_USER.ID));
                                    } catch (DataAccessException e) {
                                        /*IGNORE*/
                                    }
                                    return new User(
                                            record.getValue(AppUser.APP_USER.ID),
                                            record.getValue(AppUser.APP_USER.USERNAME),
                                            record.getValue(AppUser.APP_USER.PASSWORD),
                                            record.getValue(AppUser.APP_USER.EMAIL),
                                            roles
                                    );
                                }
                            });
        } catch (NamingException e) {
            throw new DataAccessException("Unable to resolve data source java:comp/env/jdbc/carat");
        }
    }

    public List<Role> getRoles(Integer userId) throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);


            return create.select().from(AppUserRole.APP_USER_ROLE).where(AppUserRole.APP_USER_ROLE.USER_ID.eq(userId))
                    .fetch(new RecordMapper<Record, Role>() {
                        @Override
                        public Role map(Record record) {
                            return new Role(
                                    record.getValue(AppUserRole.APP_USER_ROLE.USER_ROLE)
                            );
                        }
                    });
        } catch (NamingException e) {
            throw new DataAccessException("Unable to resolve data source java:comp/env/jdbc/carat");
        }
    }
}
