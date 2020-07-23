package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author redarm
 * @Date 2020/6/20 3:43 下午
 **/
@Entity
@Table(name = "profession")
public class Profession extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profession_name")
    private String name;

    @ManyToOne(targetEntity = Institute.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    public Profession(String name) {
        this.id = null;

        this.name = name;
    }

    public Profession(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profession)) return false;
        if (!super.equals(o)) return false;
        Profession that = (Profession) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getInstitute(), that.getInstitute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public Profession(Institute institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", institute=" + institute +
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

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}

