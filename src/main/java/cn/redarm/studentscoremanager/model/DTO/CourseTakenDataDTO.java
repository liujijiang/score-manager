package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/7 2:43 下午
 **/
@Data
@ToString
public class CourseTakenDataDTO {

    @NotBlank(message = "institute name can't be null")
    private String instituteName;
}
