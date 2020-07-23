package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseMarkDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;

public interface CourseMarkService {

    /**
     * @Author Redarm
     * @Description //TODO insert a course mark
     * @Date 10:52 下午 2020/7/5
     * @Param [courseMarkDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insert(CourseMarkDTO courseMarkDTO);

    /**
     * @Author Redarm
     * @Description //TODO query course mark and return by page
     * @Date 10:52 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryCourseMarkPage(PageDTO pageDTO);

    /**
     * @Author Redarm
     * @Description //TODO delete a course mark by name
     * @Date 10:53 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult delete(String name);
    
    /**
     * @Author Redarm
     * @Description //TODO query course mark names
     * @Date 11:05 上午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryCourseMarkNames();
}
