package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/2 9:26 下午
 **/
@Data
@ToString
public class StudentPageDTO {

    private String name;

    private String institute;

    private String education;

    @NotBlank(message = "sex can't be null")
    private String sex;

    @NotBlank(message = "page can't be null")
    private String page;

    @NotBlank(message = "size can't be null")
    private String size;
}
