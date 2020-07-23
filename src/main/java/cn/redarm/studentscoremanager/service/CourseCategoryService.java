package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseCategoryDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;

public interface CourseCategoryService {

    /**
     * @Author Redarm
     * @Description //TODO insert a course category
     * @Date 10:17 下午 2020/7/5
     * @Param [courseCategoryDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insert(CourseCategoryDTO courseCategoryDTO);

    /**
     * @Author Redarm
     * @Description //TODO query course category and return by page
     * @Date 10:17 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryCourseCategoryPage(PageDTO pageDTO);

    /**
     * @Author Redarm
     * @Description //TODO delete a course category
     * @Date 10:19 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult delete(String name);

    /**
     * @Author Redarm
     * @Description //TODO return all names
     * @Date 10:18 上午 2020/7/8
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryNames();
}