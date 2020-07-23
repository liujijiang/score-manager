package cn.redarm.studentscoremanager.model.excel;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import cn.redarm.studentscoremanager.model.entity.*;
import lombok.Data;

/**
 * @Author Redarm
 * @Date 2020/7/8 9:58 上午
 **/
@Data
@Excel("学生成绩信息表")
public class ExcelCourseTaken {

    @ExcelField("学生姓名")
    private String studentName;

    @ExcelField("学生编号")
    private String number;

    @ExcelField("课程名")
    private String courseName;

    @ExcelField("课程性质")
    private String courseNatureName;

    @ExcelField("学分")
    private String credits;

    @ExcelField("成绩")
    private String score;

    @ExcelField("绩点")
    private String gradePoint;

    @ExcelField("成绩性质")
    private String scoreNatureName;

    @ExcelField("是否作废")
    private String invalid;

    @ExcelField("课程标记")
    private String courseMarkName;

    @ExcelField("学年")
    private String schoolYear;

    @ExcelField("学期")
    private String semester;

    public ExcelCourseTaken(CourseTaken courseTaken){
        this.studentName = courseTaken.getStudent().getName();
        this.number = courseTaken.getStudent().getNumber();
        this.courseName = courseTaken.getCourse().getName();
        this.courseNatureName = courseTaken.getCourseNature().getName();
        this.credits = courseTaken.getCredits();
        this.score = courseTaken.getScore();
        this.gradePoint = courseTaken.getGradePoint();
        this.scoreNatureName = courseTaken.getScoreNature().getName();
        this.invalid = courseTaken.getInvalid();
        this.courseMarkName = courseTaken.getCourseMark().getName();
        this.schoolYear = courseTaken.getSchoolYear();
        this.semester = courseTaken.getSemester();
    }

    public ExcelCourseTaken(){

    }
}
