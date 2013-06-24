package za.co.yellowfire.carat.db;

import org.jooq.Record;

import java.io.Serializable;

public class Item implements Serializable {
    private Integer id;
    private String description;

    public Item(Record record) {
        this.id = record.getValue(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.ID);
        this.description = record.getValue(za.co.yellowfire.carat.db.postgres.tables.Item.ITEM.DESCRIPTION);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "id = " + id + " description = " + description;
    }
}
