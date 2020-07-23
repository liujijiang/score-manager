package cn.redarm.studentscoremanager.model.excel;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import cn.redarm.studentscoremanager.model.entity.Student;
import lombok.Data;

/**
 * @Author Redarm
 * @Date 2020/7/8 9:01 上午
 **/
@Data
@Excel("学生表")
public class ExcelStudent {

    @ExcelField("姓名")
    private String name;

    @ExcelField("学号")
    private String number;

    @ExcelField("入学时间")
    private String time;

    @ExcelField("性别")
    private String sex;

    @ExcelField("生日")
    private String birthday;

    @ExcelField("民族")
    private String nation;

    // 籍贯
    @ExcelField("籍贯")
    private String hometown;

    // 生源地
    @ExcelField("生源地")
    private String birthPlace;

    // 地址
    @ExcelField("地址")
    private String address;

    @ExcelField("学院")
    private String instituteName;

    public ExcelStudent(){

    }

    public ExcelStudent(Student student){
        this.name = student.getName();
        this.number = student.getNumber();
        this.time = student.getTime();
        this.sex = student.getSex();
        this.birthday = student.getBirthday();
        this.nation = student.getNation();
        this.hometown = student.getHometown();
        this.birthPlace = student.getBirthPlace();
        this.address = student.getAddress();
        this.instituteName = student.getInstitute().getName();
    }
}
