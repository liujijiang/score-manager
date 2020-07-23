package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.StudentDTO;
import cn.redarm.studentscoremanager.model.DTO.StudentPageDTO;
import cn.redarm.studentscoremanager.model.excel.ExcelStudent;

import java.util.List;

public interface StudentService {

    /**
     * @Author Redarm
     * @Description //TODO insert a student
     * @Date 8:53 下午 2020/7/2
     * @Param [studentDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insertStudent(StudentDTO studentDTO);

    /**
     * @Author Redarm
     * @Description //TODO query student by name or institute name or euucation name with sex and returen in page
     * @Date 9:29 下午 2020/7/2
     * @Param [studentPageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryStudentPage(StudentPageDTO studentPageDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO delete a student by name
     * @Date 4:29 下午 2020/7/4
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteStudent(String name);
    
    /**
     * @Author Redarm
     * @Description //TODO update student
     * @Date 9:10 下午 2020/7/4
     * @Param [studentDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult updateStudent(StudentDTO studentDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO query student names
     * @Date 2:44 下午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryStudentNames();

    /**
     * @Author Redarm
     * @Description //TODO return excel
     * @Date 10:18 上午 2020/7/8
     * @Param []
     * @return java.util.List<cn.redarm.studentscoremanager.model.excel.ExcelStudent>
    **/
    List<ExcelStudent> getExcel();
}
