package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author redarm
 * @Date 2020/6/20 4:16 下午
 **/
@Entity
@Table(name = "course_token")
public class CourseTaken extends BaseEntity implements Comparable<CourseTaken>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToOne(targetEntity = CourseNature.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_nature_id", referencedColumnName = "id")
    private CourseNature courseNature;

    @Column(name = "credits")
    private String credits;

    @Column(name = "score")
    private String score;

    @Column(name = "grade_point")
    private String gradePoint;

    @ManyToOne(targetEntity = ScoreNature.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "score_nature_id", referencedColumnName = "id")
    private ScoreNature scoreNature;

    @Column(name = "invalid")
    private String invalid;

    @ManyToOne(targetEntity = CourseMark.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_mark_id", referencedColumnName = "id")
    private CourseMark courseMark;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "semester")
    private String semester;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseTaken)) return false;
        if (!super.equals(o)) return false;
        CourseTaken that = (CourseTaken) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCourse(), that.getCourse()) &&
                Objects.equals(getCourseNature(), that.getCourseNature()) &&
                Objects.equals(getCredits(), that.getCredits()) &&
                Objects.equals(getScore(), that.getScore()) &&
                Objects.equals(getGradePoint(), that.getGradePoint()) &&
                Objects.equals(getScoreNature(), that.getScoreNature()) &&
                Objects.equals(getInvalid(), that.getInvalid()) &&
                Objects.equals(getCourseMark(), that.getCourseMark()) &&
                Objects.equals(getSchoolYear(), that.getSchoolYear()) &&
                Objects.equals(getSemester(), that.getSemester()) &&
                Objects.equals(getStudent(), that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCredits(), getScore(), getGradePoint(), getInvalid(), getSchoolYear(), getSemester());
    }

    public CourseTaken() {
    }

    @Override
    public String toString() {
        return "CourseToken{" +
                "id=" + id +
                ", course=" + course +
                ", courseNature=" + courseNature +
                ", credits='" + credits + '\'' +
                ", score='" + score + '\'' +
                ", gradePoint='" + gradePoint + '\'' +
                ", scoreNature=" + scoreNature +
                ", invalid=" + invalid +
                ", courseMark=" + courseMark +
                ", schoolYear='" + schoolYear + '\'' +
                ", semester='" + semester + '\'' +
                ", student=" + student +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseNature getCourseNature() {
        return courseNature;
    }

    public void setCourseNature(CourseNature courseNature) {
        this.courseNature = courseNature;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(String gradePoint) {
        this.gradePoint = gradePoint;
    }

    public ScoreNature getScoreNature() {
        return scoreNature;
    }

    public void setScoreNature(ScoreNature scoreNature) {
        this.scoreNature = scoreNature;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public CourseMark getCourseMark() {
        return courseMark;
    }

    public void setCourseMark(CourseMark courseMark) {
        this.courseMark = courseMark;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int compareTo(CourseTaken o) {
        double theScore = Double.parseDouble(this.getScore());
        double thatScore = Double.parseDouble(o.getScore());

        return theScore-thatScore > 0.0 ? 1 : theScore-thatScore == 0.0 ? 0 : -1;
    }
}
