package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/19 3:22 下午
 **/
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Transient
    @OneToMany(targetEntity = UserRole.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "role")
    private Set<UserRole> users = new HashSet<>();

    public Role() {

    }

    public Role(String name) {
        this.id = null;

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                Objects.equals(getName(), role.getName()) &&
                Objects.equals(getUsers(), role.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRole> users) {
        this.users = users;
    }
}
