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
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;

    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>(0);
    }

    public User(Integer id, String name, String password, String firstName, String lastName, String email, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = new ArrayList<>(roles != null ? roles.size() : 0);
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }

    public List<Role> getRoles() {
        List<Role> results = new ArrayList<>(roles.size());
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
