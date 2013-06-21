package za.co.yellowfire.carat.db;


import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.io.Closeable;
import java.util.List;

@RegisterMapper(ItemMapper.class)
public interface ItemDao extends Closeable {

    @SqlUpdate("create table if not exists item (id int identity primary key, value varchar(100), version int)")
    void createTable();

    @SqlUpdate("insert into item (value, version) values (:value, 1)")
    void insert(@Bind("value") String value);

    @SqlQuery("select id, value, version from item")
    List<Item> findAll();

    void close();
}
