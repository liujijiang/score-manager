package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.EducationDTO;
import cn.redarm.studentscoremanager.model.DTO.EducationDeleteDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;

import java.awt.print.Pageable;

public interface EducationService {

    /**
     * @Author Redarm
     * @Description //TODO insert a education
     * @Date 8:52 下午 2020/7/2
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insertEducation(EducationDTO educationDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO query all education names
     * @Date 10:35 下午 2020/7/2
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryEducationNames();
    
    /**
     * @Author Redarm
     * @Description //TODO query education page
     * @Date 7:52 下午 2020/7/7
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryEducationPage(PageDTO pageDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO delete a education
     * @Date 8:51 下午 2020/7/7
     * @Param [educationDeleteDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteEducation(EducationDeleteDTO educationDeleteDTO);
}
