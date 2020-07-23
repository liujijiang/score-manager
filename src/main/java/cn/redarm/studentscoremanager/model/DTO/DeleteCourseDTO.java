package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/25 8:16 下午
 **/
@Data
@ToString
public class DeleteCourseDTO {

    @NotBlank(message = "course name can't be null")
    private String name;
}
