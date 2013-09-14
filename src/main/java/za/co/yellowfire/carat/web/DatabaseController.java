package za.co.yellowfire.carat.web;

import com.googlecode.flyway.core.Flyway;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Messages;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Slf4j @Named @ApplicationScoped
public class DatabaseController {
    private static String DATA_SOURCE = "java:comp/env/jdbc/carat";

    private DataSource getDataSource() throws NamingException {
        return (DataSource) new InitialContext().lookup(DATA_SOURCE);
    }

    /**
     *
     * @return The number of successfully applied migrations.
     * @throws com.googlecode.flyway.core.api.FlywayException when migration failed.
     * @throws javax.naming.NamingException when the datasource could not be resolved
     */
    public void onClean() throws NamingException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setLocations("classpath:db.migration.postgres");
        flyway.setSchemas("base");
        flyway.setInitOnMigrate(true);
        flyway.clean();

        Messages.addGlobalInfo("Database cleaned");
    }

    /**
     *
     * @return The number of successfully applied migrations.
     * @throws com.googlecode.flyway.core.api.FlywayException when migration failed.
     * @throws javax.naming.NamingException when the datasource could not be resolved
     */
    public int onMigrate() throws NamingException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setLocations("classpath:db.migration.postgres");
        flyway.setSchemas("base");
        flyway.setInitOnMigrate(true);

        int migrations = flyway.migrate();
        Messages.addGlobalInfo("Database migrated with {0} migrations", migrations);

        return migrations;
    }
}
