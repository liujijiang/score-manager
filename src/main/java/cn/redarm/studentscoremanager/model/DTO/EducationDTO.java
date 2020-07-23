package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/2 8:52 下午
 **/
@Data
@ToString
public class EducationDTO {

    @NotBlank(message = "name can't be null")
    private String name;

    @NotBlank(message = "time can't be null")
    private String time;
}
