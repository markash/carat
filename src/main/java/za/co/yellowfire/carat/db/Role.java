package za.co.yellowfire.carat.db;

import lombok.Getter;
import lombok.Setter;

public class Role {
    @Getter @Setter
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
