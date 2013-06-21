package za.co.yellowfire.carat.db;

import java.io.Serializable;

public class Item implements Serializable {
    private Long id;
    private String value;
    private Long version;

    public Item() { }

    public Item(long id, String value, long version) {
        this.id = id;
        this.value = value;
        this.version = version;
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
