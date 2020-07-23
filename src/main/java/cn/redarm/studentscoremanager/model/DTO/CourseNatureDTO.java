package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/5 7:46 下午
 **/
@Data
@ToString
public class CourseNatureDTO {

    @NotBlank(message = "coursenature name can't be null")
    private String name;
}
