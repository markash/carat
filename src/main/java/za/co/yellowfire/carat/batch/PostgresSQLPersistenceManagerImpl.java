package za.co.yellowfire.carat.batch;

import com.ibm.jbatch.container.exception.BatchContainerServiceException;
import com.ibm.jbatch.container.exception.PersistenceException;
import com.ibm.jbatch.container.services.impl.JDBCPersistenceManagerImpl;
import com.ibm.jbatch.spi.services.IBatchConfig;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class PostgresSQLPersistenceManagerImpl extends JDBCPersistenceManagerImpl {
    private IBatchConfig batchConfig;


    @Override
    public void init(IBatchConfig batchConfig) throws BatchContainerServiceException {
        /* Override so that we can get access to IBatchConfig */
        this.batchConfig = batchConfig;
        /* Continue processing the parent class */
        super.init(batchConfig);
    }

    protected Connection getConnection() throws SQLException {
        log.trace("Entering: getConnection");
        Connection connection = null;

        if(!batchConfig.isJ2seMode()) {
            log.debug("J2EE mode, getting connection from data source");
            connection = dataSource.getConnection();
            log.debug("autocommit="+connection.getAutoCommit());
        } else {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new PersistenceException(e);
            }
            log.debug("JSE mode, getting connection from " + url);
            connection = DriverManager.getConnection(url, userId, pwd);
            log.debug("autocommit="+connection.getAutoCommit());
        }
        setSchemaOnConnection(connection);

        log.trace("Exiting: getConnection() with conn =" + connection);
        return connection;
    }

    /**
     * Set the default schema JBATCH or the schema defined in batch-config on the connection object.
     *
     * @param connection The connection to set the schema on
     * @throws SQLException If there was a SQL error
     */
    private void setSchemaOnConnection(Connection connection) throws SQLException {
        log.trace("Entering setSchemaOnConnection()");

        PreparedStatement ps = null;
        ps = connection.prepareStatement("set search_path to " + schema + ", public");
        //ps.setString(1, schema);
        ps.executeUpdate();

        ps.close();

        log.trace("Exiting setSchemaOnConnection()");
    }
}
