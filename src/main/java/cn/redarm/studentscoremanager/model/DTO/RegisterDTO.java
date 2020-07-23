package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/19 4:14 下午
 **/
@Data
@ToString
public class RegisterDTO {

    @NotBlank(message = "username can't be null")
    private String username;

    @NotBlank(message = "password can't be null")
    private String password;

    @NotBlank(message = "nickName can't be null")
    private String nickName;

    @NotBlank(message = "email can't be null")
    private String email;

}
