package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:48 下午
 **/
@Entity
@Table(name = "course_mark")
public class CourseMark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_mark_name")
    private String name;

    @Transient
    @OneToMany(targetEntity = CourseTaken.class, cascade = CascadeType.REMOVE, mappedBy = "courseMark", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CourseTaken> courseTakens = new HashSet<>();

    public CourseMark() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseMark)) return false;
        if (!super.equals(o)) return false;
        CourseMark that = (CourseMark) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCourseTakens(), that.getCourseTakens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "CourseMark{" +
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

    public Set<CourseTaken> getCourseTakens() {
        return courseTakens;
    }

    public void setCourseTakens(Set<CourseTaken> courseTakens) {
        this.courseTakens = courseTakens;
    }
}
