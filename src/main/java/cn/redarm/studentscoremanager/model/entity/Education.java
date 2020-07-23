package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:27 下午
 **/
@Entity
@Table(name = "education")
public class Education extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "study_year")
    private String year;

    @Transient
    @OneToMany(targetEntity = StudentEducation.class, cascade = CascadeType.REMOVE, mappedBy = "education", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<StudentEducation> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Education)) return false;
        if (!super.equals(o)) return false;
        Education education = (Education) o;
        return Objects.equals(getId(), education.getId()) &&
                Objects.equals(getName(), education.getName()) &&
                Objects.equals(getYear(), education.getYear()) &&
                Objects.equals(getStudents(), education.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public Education() {
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Set<StudentEducation> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEducation> students) {
        this.students = students;
    }
}
