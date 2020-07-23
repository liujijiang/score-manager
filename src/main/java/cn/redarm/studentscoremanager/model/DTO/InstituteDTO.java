package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/24 1:22 下午
 **/
@Data
@ToString
public class InstituteDTO {

    @NotBlank(message = "name can't be null")
    String instituteName;

    @NotBlank(message = "profession can't be null")
    String professions;
}
