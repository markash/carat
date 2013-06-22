package za.co.yellowfire.carat.db;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
public class ItemDao implements Serializable {

    @Resource(name = "jdbc/carat")
    private DataSource dataSource;

    public List<Item> getItems() {
        try {
            DSLContext create = DSL.using(dataSource, SQLDialect.HSQLDB);

            List<Item> results = new ArrayList<Item>();
            Result<Record> result = create.select().from("ITEM").fetch();
            for (Record record : result) {
                results.add(new Item(record));
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
