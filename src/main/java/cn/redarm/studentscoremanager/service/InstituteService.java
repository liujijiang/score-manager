package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.InstituteDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;

public interface InstituteService {

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query institute
     * @Date 10:45 上午 2020/6/24
     * @Param []
     **/
    CommonResult queryInstiture();

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO add a institute
     * @Date 1:25 下午 2020/6/24
     * @Param [instituteDTO]
     **/
    CommonResult insertInstiture(InstituteDTO instituteDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO query all institutes and return by page
     * @Date 11:15 下午 2020/7/4
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryInstitutePage(PageDTO pageDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO count the number of student in this institute
     * @Date 8:37 上午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult countStudentNum(String instituteName);
    
    /**
     * @Author Redarm
     * @Description //TODO count the number of profession in this institute
     * @Date 8:37 上午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult countProfessionNum(String instituteName);
    
    /**
     * @Author Redarm
     * @Description //TODO count the number of course in this institute
     * @Date 8:38 上午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult countCourseNum(String instituteName);

    /**
     * @Author Redarm
     * @Description //TODO a util count the num by institute name
     * @Date 3:16 下午 2020/7/5
     * @Param [instituteName, o]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult countNumByInstituteName(String instituteName, Object o);
    
    /**
     * @Author Redarm
     * @Description //TODO delete a institute by institute name
     * @Date 3:16 下午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteInstitute(String instituteName);
    
    /**
     * @Author Redarm
     * @Description //TODO update institute 
     * @Date 4:13 下午 2020/7/5
     * @Param [instituteDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult updateInstitute(InstituteDTO instituteDTO);
}
