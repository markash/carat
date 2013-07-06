package za.co.yellowfire.carat.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
import static za.co.yellowfire.carat.db.postgres.tables.AppUser.*;
import static za.co.yellowfire.carat.db.postgres.tables.AppRole.*;

@Slf4j @Named
public class UserDao implements Dao<User>, Serializable {
    private static String DATA_SOURCE = "java:comp/env/jdbc/carat";

    private DataSource getDataSource() throws NamingException {
        return (DataSource) new InitialContext().lookup(DATA_SOURCE);
    }

    public User create(User user) throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            Record record =
            create.insertInto(APP_USER,
                    APP_USER.USERNAME, APP_USER.PASSWORD, APP_USER.EMAIL)
                    .values(user.getName(), new Sha256Hash(user.getPassword()).toHex(), user.getEmail())
                            .returning(APP_USER.ID)
                            .fetchOne();

            return getUser(record.getValue(APP_USER.ID));
        } catch (NamingException e) {
            log.error("Unable to resolve data source {}", DATA_SOURCE);
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
        }
    }

    public User update(User user) throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            create.update(APP_USER)
                            .set(APP_USER.PASSWORD, new Sha256Hash(user.getPassword()).toHex())
                            .set(APP_USER.FIRST_NAME, user.getFirstName())
                            .set(APP_USER.LAST_NAME, user.getLastName())
                            .set(APP_USER.EMAIL, user.getEmail())
                            .where(APP_USER.ID.eq(user.getId()));

            return getUser(user.getId());
        } catch (NamingException e) {
            log.error("Unable to resolve data source {}", DATA_SOURCE);
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
        }
    }

    public User getUser(Integer userId) throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            List<User> results =  create.select().from(AppUser.APP_USER).where(APP_USER.ID.eq(userId)).fetch(new UserRecordMapper());
            if (results.size() > 0) {
                return results.get(0);
            }
            return null;
        } catch (NamingException e) {
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
        }
    }

    public User getUser(String userName) throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            List<User> results =  create.select().from(AppUser.APP_USER).where(APP_USER.USERNAME.eq(userName)).fetch(new UserRecordMapper());
            if (results.size() > 0) {
                return results.get(0);
            }
            return null;
        } catch (NamingException e) {
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
        }
    }

    public List<User> retrieve() throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            return create.select().from(AppUser.APP_USER).fetch(new UserRecordMapper());
        } catch (NamingException e) {
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
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
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
        }
    }

    public List<Role> getRoles() throws DataAccessException {
        try {
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            return create.select().from(APP_ROLE).fetch(new RoleRecordMapper());
        } catch (NamingException e) {
            throw new DataAccessException("Unable to resolve data source " + DATA_SOURCE);
        }
    }

    private class RoleRecordMapper implements RecordMapper<Record, Role> {
        @Override
        public Role map(Record record) {
            if (record.size() > 0)
                return new Role( record.getValue(APP_ROLE.ROLE));
            else
                return null;
        }
    }

    private class UserRecordMapper implements RecordMapper<Record, User> {
        @Override
        public User map(Record record) {
            List<Role> roles = new ArrayList<>(0);
            try {
                roles = getRoles(record.getValue(AppUser.APP_USER.ID));
            } catch (DataAccessException e) {
                                        /*IGNORE*/
            }
            return new User(
                    record.getValue(AppUser.APP_USER.ID),
                    record.getValue(AppUser.APP_USER.USERNAME),
                    record.getValue(AppUser.APP_USER.PASSWORD),
                    record.getValue(AppUser.APP_USER.FIRST_NAME),
                    record.getValue(AppUser.APP_USER.LAST_NAME),
                    record.getValue(AppUser.APP_USER.EMAIL),
                    roles
            );
        }
    }
}
