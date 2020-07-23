package cn.redarm.studentscoremanager.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author redarm
 * @Date 2020/6/20 3:58 下午
 **/
@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teacher_name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "number")
    private String number;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "money")
    private String money;

    @OneToOne(fetch = FetchType.EAGER)
    private Institute institute;

    @Column(name = "time")
    private String time;

    @Transient
    @OneToMany(targetEntity = TeacherCourse.class,cascade = CascadeType.REMOVE, mappedBy = "teacher", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<TeacherCourse> courses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getId(), teacher.getId()) &&
                Objects.equals(getName(), teacher.getName()) &&
                Objects.equals(getSex(), teacher.getSex()) &&
                Objects.equals(getNumber(), teacher.getNumber()) &&
                Objects.equals(getBirthday(), teacher.getBirthday()) &&
                Objects.equals(getMoney(), teacher.getMoney()) &&
                Objects.equals(getInstitute(), teacher.getInstitute()) &&
                Objects.equals(getTime(), teacher.getTime()) &&
                Objects.equals(getCourses(), teacher.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", number='" + number + '\'' +
                ", birthday='" + birthday + '\'' +
                ", money='" + money + '\'' +
                ", institute=" + institute +
                ", time='" + time + '\'' +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<TeacherCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<TeacherCourse> courses) {
        this.courses = courses;
    }
}
