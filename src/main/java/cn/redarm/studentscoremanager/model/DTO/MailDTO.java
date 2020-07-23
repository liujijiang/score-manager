package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/7 12:32 下午
 **/
@Data
@ToString
public class MailDTO {

    @NotBlank(message = "mail address can't be null")
    private String mailAddress;

    @NotBlank(message = "msg can't be null")
    private String msg;
}
