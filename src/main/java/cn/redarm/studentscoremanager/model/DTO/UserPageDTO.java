package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/1 9:54 下午
 **/
@Data
@ToString
public class UserPageDTO {

    private String username;

    private String roleName;

    @NotBlank(message = "page can't be null")
    private String page;

    @NotBlank(message = "size can't be null")
    private String size;
}
