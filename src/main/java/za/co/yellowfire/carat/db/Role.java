package za.co.yellowfire.carat.db;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Role implements Serializable {
    @Getter @Setter
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
