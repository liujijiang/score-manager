package cn.redarm.studentscoremanager.model.VO;

import cn.redarm.studentscoremanager.model.entity.Institute;

import java.util.Objects;

/**
 * @Author Redarm
 * @Date 2020/7/5 10:15 上午
 **/
public class InstituteVO {

    private String name;

    private String createDate;

    private Long studentNum;

    private Long professionNum;

    private Long courseNum;

    public InstituteVO(){

    }

    public InstituteVO(Institute institute){
        this.name = institute.getName();
        this.createDate = institute.getCreateDate();

        this.studentNum = 0L;
        this.courseNum = 0L;
        this.professionNum = 0L;
    }

    @Override
    public String toString() {
        return "InstituteVO{" +
                "name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                ", studentNum=" + studentNum +
                ", professionNum=" + professionNum +
                ", courseNum=" + courseNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstituteVO)) return false;
        InstituteVO that = (InstituteVO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCreateDate(), that.getCreateDate()) &&
                Objects.equals(getStudentNum(), that.getStudentNum()) &&
                Objects.equals(getProfessionNum(), that.getProfessionNum()) &&
                Objects.equals(getCourseNum(), that.getCourseNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCreateDate(), getStudentNum(), getProfessionNum(), getCourseNum());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Long studentNum) {
        this.studentNum = studentNum;
    }

    public Long getProfessionNum() {
        return professionNum;
    }

    public void setProfessionNum(Long professionNum) {
        this.professionNum = professionNum;
    }

    public Long getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Long courseNum) {
        this.courseNum = courseNum;
    }
}
