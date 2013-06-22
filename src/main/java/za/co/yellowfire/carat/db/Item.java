package za.co.yellowfire.carat.db;

import org.jooq.Record;

import java.io.Serializable;

public class Item implements Serializable {
    private Long id;
    private String value;
    private Long version;

    public Item() { }

    public Item(Record record) {
        this.id = record.getValue("ID", Long.class);
        this.value = record.getValue("VALUE", String.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
