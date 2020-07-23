package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseCategoryDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/5 10:37 下午
 **/
@RestController
@RequestMapping(value = "/api/courseCategory")
@Api(value = "course_category_controller")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @PostMapping(value = "insertCourseCategory")
    @ApiOperation(value = "insert_course_category")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertCourseCategory(@RequestBody @Valid CourseCategoryDTO courseCategoryDTO){
        return courseCategoryService.insert(courseCategoryDTO);
    }

    @PostMapping(value = "queryCourseCategoryPage")
    @ApiOperation(value = "query_course_category_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourseCategoryPage(@RequestBody @Valid PageDTO pageDTO){
        return courseCategoryService.queryCourseCategoryPage(pageDTO);
    }

    @PostMapping(value = "deleteCourseCategory")
    @ApiOperation(value = "delete_course_category")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteCourseCategory(@RequestBody String name){
        return courseCategoryService.delete(name);
    }

    @GetMapping(value = "queryNames")
    @ApiOperation(value = "query_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryNames(){
        return courseCategoryService.queryNames();
    }
}
