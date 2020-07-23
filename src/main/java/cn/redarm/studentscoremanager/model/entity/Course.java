package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:34 下午
 **/
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_number")
    private String number;

    @ManyToOne(targetEntity = Institute.class,cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @Column(name = "total_credits")
    private String totalCredits;

    @ManyToOne(targetEntity = CourseCategory.class,  fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CourseCategory category;

    @Transient
    @OneToMany(targetEntity = TeacherCourse.class, mappedBy = "course", fetch = FetchType.EAGER)
    private Set<TeacherCourse> teachers = new HashSet<>();

    @Column(name = "exam_method")
    private String examMethod;

    @Transient
    @OneToMany(targetEntity = CourseTaken.class, cascade = CascadeType.REMOVE, mappedBy = "course", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CourseTaken> courseTakens = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) &&
                Objects.equals(getName(), course.getName()) &&
                Objects.equals(getNumber(), course.getNumber()) &&
                Objects.equals(getInstitute(), course.getInstitute()) &&
                Objects.equals(getTotalCredits(), course.getTotalCredits()) &&
                Objects.equals(getCategory(), course.getCategory()) &&
                Objects.equals(getTeachers(), course.getTeachers()) &&
                Objects.equals(getExamMethod(), course.getExamMethod()) &&
                Objects.equals(getCourseTokens(), course.getCourseTokens());
    }

    public Course() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumber());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", institute=" + institute +
                ", totalCredits='" + totalCredits + '\'' +
                ", category=" + category +
                ", examMethod='" + examMethod + '\'' +
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(String totalCredits) {
        this.totalCredits = totalCredits;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }

    public Set<TeacherCourse> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherCourse> teachers) {
        this.teachers = teachers;
    }

    public String getExamMethod() {
        return examMethod;
    }

    public void setExamMethod(String examMethod) {
        this.examMethod = examMethod;
    }

    public Set<CourseTaken> getCourseTokens() {
        return courseTakens;
    }

    public void setCourseTokens(Set<CourseTaken> courseTakens) {
        this.courseTakens = courseTakens;
    }
}
