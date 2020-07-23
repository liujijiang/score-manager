package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/24 8:32 下午
 **/
@Data
@ToString
public class TeacherDTO {

    @NotBlank(message = "name can't be null")
    private String name;

    @NotBlank(message = "sex can't be null")
    private String sex;

    @NotBlank(message = "number can't be null")
    private String number;

    @NotBlank(message = "birthday can't be null")
    private String birthday;

    @NotBlank(message = "money can't be null")
    private String money;

    @NotBlank(message = "institute name can't be null")
    private String instituteName;

    @NotBlank(message = "time can't be null")
    private String time;
}
