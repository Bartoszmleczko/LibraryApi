package pl.mleczkobartosz.LibraryApi.Entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return name;
    }

    public void setRole_name(String name) {
        this.name = name;
    }
}
