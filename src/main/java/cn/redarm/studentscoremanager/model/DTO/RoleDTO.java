package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/7 8:15 上午
 **/
@Data
@ToString
public class RoleDTO {

    @NotBlank(message = "role name can't be null")
    private String name;
}
