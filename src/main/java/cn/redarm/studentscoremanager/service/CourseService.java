package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseDTO;
import cn.redarm.studentscoremanager.model.DTO.CoursePageDTO;
import cn.redarm.studentscoremanager.model.DTO.DeleteCourseDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;

public interface CourseService {

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query course names
     * @Date 5:00 下午 2020/6/24
     * @Param []
     **/
    CommonResult queryCourse();

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO insert a course
     * @Date 5:58 下午 2020/6/24
     * @Param []
     **/
    CommonResult insertCourse(CourseDTO courseDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query all courses
     * @Date 8:59 下午 2020/6/24
     * @Param []
     **/
    CommonResult queryCourses(PageDTO pageDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query course by page
     * @Date 9:55 上午 2020/6/25
     * @Param [coursePageDTO]
     **/
    CommonResult queryCoursePage(CoursePageDTO coursePageDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO delete course by course name
     * @Date 12:29 下午 2020/6/26
     * @Param [deleteCourseDTO]
     **/
    CommonResult deleteCourse(DeleteCourseDTO deleteCourseDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO update course
     * @Date 1:15 下午 2020/6/27
     * @Param [courseDTO]
     **/
    CommonResult updateCourse(CourseDTO courseDTO);
}
