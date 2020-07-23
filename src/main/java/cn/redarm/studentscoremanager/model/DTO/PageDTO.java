package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author redarm
 * @Date 2020/6/25 8:35 上午
 **/
@Data
@ToString
public class PageDTO {

    @NotBlank(message = "page can't be null")
    private String page;

    @NotBlank(message = "size can't be null")
    private String size;
}
