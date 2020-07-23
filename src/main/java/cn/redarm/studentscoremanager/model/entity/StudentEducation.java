package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author Redarm
 * @Date 2020/7/3 9:10 上午
 **/
@Entity
@Table(name = "sys_student_education")
public class StudentEducation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Student.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(targetEntity = Education.class,cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name = "education_id", referencedColumnName = "id")
    private Education education;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentEducation)) return false;
        if (!super.equals(o)) return false;
        StudentEducation that = (StudentEducation) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getStudent(), that.getStudent()) &&
                Objects.equals(getEducation(), that.getEducation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public StudentEducation() {
    }

    @Override
    public String toString() {
        return "StudentEducation{" +
                "id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }
}
