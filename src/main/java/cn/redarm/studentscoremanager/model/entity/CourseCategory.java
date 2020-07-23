package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:46 下午
 **/
@Entity
@Table(name = "course_category")
public class CourseCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_category_name")
    private String name;

    @Transient
    @OneToMany(targetEntity = Course.class, cascade = CascadeType.REMOVE, mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

    public CourseCategory(String name) {
        this.id = null;

        this.name = name;
    }

    public CourseCategory(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCategory)) return false;
        if (!super.equals(o)) return false;
        CourseCategory that = (CourseCategory) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCourses(), that.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public CourseCategory(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CourseCategory{" +
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
