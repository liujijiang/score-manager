package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/7 10:46 上午
 **/
@Data
@ToString
public class UserAddRoleDTO {

    @NotBlank(message = "username can't be null")
    private String username;

    @NotBlank(message = "role name can't be null")
    private String roleName;
}
