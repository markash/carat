package za.co.yellowfire.carat.db;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String email;

    private List<Role> roles;

    public User(Integer id, String name, String password, String email, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = new ArrayList<Role>(roles != null ? roles.size() : 0);
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }

    public List<Role> getRoles() {
        List<Role> results = new ArrayList<Role>(roles.size());
        Collections.copy(results, roles);
        return results;
    }

    public void setRoles(List<Role> roles) {
        this.roles.clear();
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }
}
