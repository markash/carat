package za.co.yellowfire.carat.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements ResultSetMapper<Item> {
    public Item map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Item(r.getInt("id"), r.getString("value"), r.getInt("version"));
    }
}