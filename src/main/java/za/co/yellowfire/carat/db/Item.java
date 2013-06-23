package za.co.yellowfire.carat.db;

import org.jooq.Record;

import java.io.Serializable;

public class Item implements Serializable {
    private Integer id;
    private String value;
    private Long version;

    public Item() { }

    public Item(Record record) {
        this.id = record.getValue(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.ID);
        this.value = record.getValue(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.VALUE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
