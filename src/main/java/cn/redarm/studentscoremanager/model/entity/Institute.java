package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:41 下午
 **/
@Entity
@Table(name = "institute")
public class Institute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "institute_name")
    private String name;

    @Transient
    @OneToMany(targetEntity = Profession.class,cascade = CascadeType.REMOVE, mappedBy = "institute", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Profession> professions = new HashSet<>();

    @Transient
    @OneToMany(targetEntity = Student.class, cascade = CascadeType.REMOVE, mappedBy = "institute", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Student> students = new HashSet<>();

    @Transient
    @OneToMany(targetEntity = Course.class, cascade = CascadeType.REMOVE, mappedBy = "institute", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Institute)) return false;
        if (!super.equals(o)) return false;
        Institute institute = (Institute) o;
        return Objects.equals(getId(), institute.getId()) &&
                Objects.equals(getName(), institute.getName()) &&
                Objects.equals(getProfessions(), institute.getProfessions()) &&
                Objects.equals(getStudents(), institute.getStudents()) &&
                Objects.equals(getCourses(), institute.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public Institute() {
    }

    @Override
    public String toString() {
        return "Institute{" +
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

    public Set<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(Set<Profession> professions) {
        this.professions = professions;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
