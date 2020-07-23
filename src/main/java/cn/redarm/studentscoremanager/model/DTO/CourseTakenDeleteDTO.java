package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/6 8:21 下午
 **/
@Data
@ToString
public class CourseTakenDeleteDTO {

    @NotBlank(message = "course name can't be null")
    private String courseName;

    @NotBlank(message = "student name can't be null")
    private String studentName;

    @NotBlank(message = "score nature name can't be null")
    private String scoreNatureName;
}
