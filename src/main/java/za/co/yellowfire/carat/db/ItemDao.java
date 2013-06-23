package za.co.yellowfire.carat.db;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@Named
public class ItemDao implements Serializable {

    private DataSource getDataSource() throws NamingException {
        return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/carat");
    }

    public List<Item> getItems() {
        try {
            //Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/carat-db", "sa", "sa");
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);

            List<Item> results = new ArrayList<Item>();
            Result<Record> result = create.select().from("ITEM").fetch();
            for (Record record : result) {
                results.add(new Item(record));
            }
            System.out.println("results = " + results);
            return results;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ArrayList<Item>(0);
    }
}
