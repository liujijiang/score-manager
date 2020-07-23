package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseDTO;
import cn.redarm.studentscoremanager.model.DTO.CoursePageDTO;
import cn.redarm.studentscoremanager.model.DTO.DeleteCourseDTO;
import cn.redarm.studentscoremanager.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author redarm
 * @Date 2020/6/24 5:00 下午
 **/
@RestController
@RequestMapping(value = "/api/course")
@Api(value = "course_controller")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/queryCourseNames")
    @ApiOperation(value = "query_course_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourse() {
        return courseService.queryCourse();
    }

    @PostMapping(value = "/insertCourse")
    @ApiOperation(value = "insert_a_course")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertCourse(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.insertCourse(courseDTO);
    }

    @PostMapping(value = "/queryCoursePage")
    @ApiOperation(value = "query_course_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCoursePage(@RequestBody @Valid CoursePageDTO pageDTO) {
        return courseService.queryCoursePage(pageDTO);
    }

    @PostMapping(value = "/deleteCourse")
    @ApiOperation(value = "delete_course")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteCourse(@RequestBody @Valid DeleteCourseDTO deleteCourseDTO) {
        return courseService.deleteCourse(deleteCourseDTO);
    }

    @PostMapping(value = "/updateCourse")
    @ApiOperation(value = "update_course")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult updateCourse(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.updateCourse(courseDTO);
    }
}
