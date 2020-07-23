package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.TeacherDTO;
import cn.redarm.studentscoremanager.model.DTO.TeacherPageDTO;
import cn.redarm.studentscoremanager.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author redarm
 * @Date 2020/6/24 8:42 下午
 **/
@RestController
@RequestMapping(value = "/api/teacher")
@Api(value = "teacher_controller")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/insertTeacher")
    @ApiOperation(value = "insert_a_teacher")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        return teacherService.insertTeacher(teacherDTO);
    }

    @GetMapping(value = "/queryTeacherNames")
    @ApiOperation(value = "query_teacher_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryTeacherNames() {
        return teacherService.queryTeacherName();
    }

    @PostMapping(value = "/queryTeacherPage")
    @ApiOperation(value = "query_teacher_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryTeacherPage(@RequestBody @Valid TeacherPageDTO teacherPageDTO) {
        return teacherService.queryTeacherPage(teacherPageDTO);
    }

    @PostMapping(value = "/deleteTeacher")
    @ApiOperation(value = "delete_teacher")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteTeacher(@RequestBody String name) {
        return teacherService.deleteTeacher(name);
    }

    @PostMapping(value = "/updateTeacher")
    @ApiOperation(value = "update_teacher")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult updateTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(teacherDTO);
    }
}
