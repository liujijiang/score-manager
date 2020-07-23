package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/6 9:06 上午
 **/
@Data
@ToString
public class CourseTakenDTO {

    @NotBlank(message = "course name can't be null")
    private String courseName;

    @NotBlank(message = "course nature name can't be null")
    private String courseNatureName;

    @NotBlank(message = "score can't be null")
    private String score;

    @NotBlank(message = "score nature name can't be null")
    private String scoreNatureName;

    @NotBlank(message = "invalid can't be null")
    private String invalid;

    @NotBlank(message = "course mark name can't be null")
    private String courseMarkName;

    @NotBlank(message = "school year can't be null")
    private String schoolYear;

    @NotBlank(message = "semester can't be null")
    private String semester;

    @NotBlank(message = "student name can't be null")
    private String studentName;
}
