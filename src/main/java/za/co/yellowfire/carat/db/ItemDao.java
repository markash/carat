package za.co.yellowfire.carat.db;

import org.jooq.*;
import org.jooq.impl.DSL;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

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
            DSLContext create = DSL.using(getDataSource(), SQLDialect.POSTGRES);
            //final ModelMapper mapper = new ModelMapper();
            //final TypeMap<Record, Item> map = mapper.createTypeMap(Record.class, Item.class);

            List<za.co.yellowfire.carat.db.Item> results =
                    create.select().from(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM)
                            .fetch(new RecordMapper<Record, za.co.yellowfire.carat.db.Item>() {
                                @Override
                                public za.co.yellowfire.carat.db.Item map(Record record) {
                                    return new Item(
                                            record.getValue(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.ID),
                                            record.getValue(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.DESCRIPTION));
                                }
                            });
            return results;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ArrayList<Item>(0);
    }

//    private static class ItemMap extends PropertyMap<Record, za.co.yellowfire.carat.db.Item> {
//      protected void configure() {
//          Object value = source.getValue("id");
//          System.out.println("description = " + value);
//        //map().setId(source.getDescription(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.ID));
//      }
//    }
}
