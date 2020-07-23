package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author Redarm
 * @Date 2020/7/2 4:16 下午
 **/
@Data
@ToString
public class StudentDTO {

    @NotBlank(message = "name can't be null")
    private String name;

    @NotBlank(message = "number can't be null")
    private String number;

    @NotBlank(message = "The time to school can't be null")
    private String time;

    @NotBlank(message = "education can't be null")
    private String educationName;

    @NotBlank(message = "sex can't be null")
    private String sex;

    @NotBlank(message = "birthday can't be null")
    private String birthday;

    @NotBlank(message = "nation can't be null")
    private String nation;

    @NotBlank(message = "hometown can't be null")
    private String hometown;

    @NotBlank(message = "birthplace can't null")
    private String birthPlace;

    @NotBlank(message = "address can't be null")
    private String address;

    @NotBlank(message = "institute name can't be null")
    private String instituteName;
}
