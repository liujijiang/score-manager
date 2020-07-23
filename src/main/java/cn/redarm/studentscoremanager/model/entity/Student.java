package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:19 下午
 **/
@Entity
@Table(name = "Student")
public class Student extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_number")
    private String number;

    @Column(name = "admission_time")
    private String time;

    @Transient
    @OneToMany(targetEntity = StudentEducation.class, orphanRemoval = true, mappedBy = "education", fetch = FetchType.EAGER)
    private Set<StudentEducation> educations = new HashSet<>();

    @Transient
    @OneToMany(targetEntity = CourseTaken.class,cascade = CascadeType.REMOVE, mappedBy = "student", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CourseTaken> courseTakens = new HashSet<>();

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "nation")
    private String nation;

    // 籍贯
    @Column(name = "hometown")
    private String hometown;

    // 生源地
    @Column(name = "birthplace")
    private String birthPlace;

    // 地址
    @Column(name = "address")
    private String address;

    @ManyToOne(targetEntity = Institute.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getName(), student.getName()) &&
                Objects.equals(getNumber(), student.getNumber()) &&
                Objects.equals(getTime(), student.getTime()) &&
                Objects.equals(getEducations(), student.getEducations()) &&
                Objects.equals(getCourseTokens(), student.getCourseTokens()) &&
                Objects.equals(getSex(), student.getSex()) &&
                Objects.equals(getBirthday(), student.getBirthday()) &&
                Objects.equals(getNation(), student.getNation()) &&
                Objects.equals(getHometown(), student.getHometown()) &&
                Objects.equals(getBirthPlace(), student.getBirthPlace()) &&
                Objects.equals(getAddress(), student.getAddress()) &&
                Objects.equals(getInstitute(), student.getInstitute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", time='" + time + '\'' +
                ", educations=" + educations +
                ", courseTokens=" + courseTakens +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nation='" + nation + '\'' +
                ", hometown='" + hometown + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", address='" + address + '\'' +
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<StudentEducation> getEducations() {
        return educations;
    }

    public void setEducations(Set<StudentEducation> educations) {
        this.educations = educations;
    }

    public Set<CourseTaken> getCourseTokens() {
        return courseTakens;
    }

    public void setCourseTokens(Set<CourseTaken> courseTakens) {
        this.courseTakens = courseTakens;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}

