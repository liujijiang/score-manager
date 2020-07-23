package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseMarkDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.service.CourseMarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/5 11:02 下午
 **/
@RestController
@RequestMapping(value = "/api/courseMark")
@Api(value = "course_mark_controller")
public class CourseMarkController {

    @Autowired
    private CourseMarkService courseMarkService;

    @PostMapping(value = "insertCourseMark")
    @ApiOperation(value = "insert_course_mark")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertCourseMark(@RequestBody @Valid CourseMarkDTO courseMarkDTO){
        return courseMarkService.insert(courseMarkDTO);
    }

    @PostMapping(value = "queryCourseMarkPage")
    @ApiOperation(value = "query_course_mark_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourseMarkPage(@RequestBody @Valid PageDTO pageDTO){
        return courseMarkService.queryCourseMarkPage(pageDTO);
    }

    @PostMapping(value = "deleteCourseMark")
    @ApiOperation(value = "delete_course_mark")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteCourseMark(@RequestBody String name){
        return courseMarkService.delete(name);
    }

    @GetMapping(value = "queryCourseMarkNames")
    @ApiOperation(value = "query_course_mark_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourseMarkNames(){
        return courseMarkService.queryCourseMarkNames();
    }
}
