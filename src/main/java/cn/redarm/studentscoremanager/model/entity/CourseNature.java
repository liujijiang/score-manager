package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:36 下午
 **/
@Entity
@Table(name = "course_nature")
public class CourseNature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nature_name")
    private String name;

    @Transient
    @OneToMany(targetEntity = CourseTaken.class, cascade = CascadeType.REMOVE, mappedBy = "courseNature", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CourseTaken> courseTakens = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseNature)) return false;
        if (!super.equals(o)) return false;
        CourseNature that = (CourseNature) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCourseTakens(), that.getCourseTakens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public CourseNature() {
    }

    @Override
    public String toString() {
        return "CourseNature{" +
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
