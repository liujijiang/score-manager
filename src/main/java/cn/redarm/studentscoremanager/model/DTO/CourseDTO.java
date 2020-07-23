package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


/**
 * @Author redarm
 * @Date 2020/6/24 5:59 下午
 **/
@Data
@ToString
public class CourseDTO {

    @NotBlank(message = "name can't be null")
    private String name;

    @NotBlank(message = "number can't be null")
    private String number;

    @NotBlank(message = "institute name can't be null")
    private String instituteName;

    @NotBlank(message = "total credits can't be null")
    private String totalCredits;

    @NotBlank(message = "category name can't be null")
    private String categoryName;

    @NotBlank(message = "teacher name can't be null")
    private String teacherName;

    @NotBlank(message = "exam method can't be null")
    private String examMethod;
}
