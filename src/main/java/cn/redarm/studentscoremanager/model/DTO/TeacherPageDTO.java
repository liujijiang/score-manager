package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/27 10:41 上午
 **/
@Data
@ToString
public class TeacherPageDTO {

    @NotBlank(message = "page must be not null")
    private String page;

    @NotBlank(message = "size must be not null")
    private String size;

    private String instituteName;

    private String teacherName;
}

