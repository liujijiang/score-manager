package cn.redarm.studentscoremanager.model.DTO;

import lombok.Data;
import lombok.ToString;

/**
 * @Author Redarm
 * @Date 2020/7/6 3:43 下午
 **/
@Data
@ToString
public class CourseTakenPageDTO extends PageDTO{

    private String studentName;

    private String instituteName;

    private String courseName;

    private String orderFlag;
}
