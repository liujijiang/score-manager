package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDataDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDeleteDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenPageDTO;
import cn.redarm.studentscoremanager.model.excel.ExcelCourseTaken;

import java.util.List;

public interface CourseTakenService {

    /**
     * @Author Redarm
     * @Description //TODO insert a course taken
     * @Date 11:06 上午 2020/7/6
     * @Param [courseTakenDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insertCourseTaken(CourseTakenDTO courseTakenDTO);

    /**
     * @Author Redarm
     * @Description //TODO query course taken page
     * @Date 3:48 下午 2020/7/6
     * @Param [courseTakenPageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryCourseTakenPage(CourseTakenPageDTO courseTakenPageDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO delete a course taken by student name and course name
     * @Date 8:07 下午 2020/7/6
     * @Param [courseName, studentName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteCourseTaken(CourseTakenDeleteDTO courseTakenDeleteDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO update a course taken
     * @Date 8:32 下午 2020/7/6
     * @Param [courseTakenDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult updateCourseTaken(CourseTakenDTO courseTakenDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO for dash board
     * @Date 2:42 下午 2020/7/7
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult getDataOfCourseTaken(CourseTakenDataDTO courseTakenDataDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO get excel
     * @Date 10:43 上午 2020/7/8
     * @Param []
     * @return java.util.List<cn.redarm.studentscoremanager.model.excel.ExcelCourseTaken>
    **/
    List<ExcelCourseTaken> getExcel();
}
