package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseNatureDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.service.CourseNatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/5 7:53 下午
 **/
@RestController
@RequestMapping(value = "/api/courseNature")
@Api(value = "course_nature_controller")
public class CourseNatureController {

    @Autowired
    private CourseNatureService courseNatureService;

    @PostMapping(value = "/insertCourseNature")
    @ApiOperation(value = "insert_course_nature")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertCourseNature(@RequestBody @Valid CourseNatureDTO courseNatureDTO){
        return courseNatureService.insertCourseNature(courseNatureDTO);
    }

    @PostMapping(value = "/queryCourseNaturePage")
    @ApiOperation(value = "query_course_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourseNaturePage(@RequestBody @Valid PageDTO pageDTO){
        return courseNatureService.queryCourseNaturePage(pageDTO);
    }

    @PostMapping(value = "/deleteCourseNature")
    @ApiOperation(value = "delete_course_nature")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteCourseNature(@RequestBody String name){
        return courseNatureService.deleteCourseNature(name);
    }

    @GetMapping(value = "/queryCourseNatureNames")
    @ApiOperation(value = "query_course_nature_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourseNatureNames(){
        return courseNatureService.queryCourseNatureNames();
    }
}
