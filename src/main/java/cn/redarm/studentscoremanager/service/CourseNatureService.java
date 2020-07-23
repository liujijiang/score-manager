package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseNatureDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;

public interface CourseNatureService {

    /**
     * @Author Redarm
     * @Description //TODO insert a course nature by name
     * @Date 7:47 下午 2020/7/5
     * @Param [courseNatureDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insertCourseNature(CourseNatureDTO courseNatureDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO query course nature page
     * @Date 8:29 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryCourseNaturePage(PageDTO pageDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO delete a course nature by name
     * @Date 9:13 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteCourseNature(String name);
    
    /**
     * @Author Redarm
     * @Description //TODO query all course nature names
     * @Date 10:32 上午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryCourseNatureNames();
}
