package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author Redarm
 * @Date 2020/7/6 9:30 下午
 **/
@Entity
@Table(name = "sys_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public UserRole() {
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(getId(), userRole.getId()) &&
                Objects.equals(getUser(), userRole.getUser()) &&
                Objects.equals(getRole(), userRole.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getRole());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
