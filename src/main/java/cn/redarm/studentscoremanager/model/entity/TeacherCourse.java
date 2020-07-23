package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author Redarm
 * @Date 2020/7/3 10:11 上午
 **/
@Entity
@Table(name = "sys_teacher_course")
public class TeacherCourse extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherCourse)) return false;
        if (!super.equals(o)) return false;
        TeacherCourse that = (TeacherCourse) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTeacher(), that.getTeacher()) &&
                Objects.equals(getCourse(), that.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public TeacherCourse() {

    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", course=" + course +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
