package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/19 4:13 下午
 **/
@Data
@ToString
public class LoginDTO {

    @NotBlank(message = "username can't be null")
    private String username;

    @NotBlank(message = "password can't be null")
    private String password;
}
