package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.InstituteDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.service.InstituteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author redarm
 * @Date 2020/6/24 1:33 下午
 **/
@RestController
@RequestMapping(value = "/api/institute")
@Api(value = "institute_controller")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @GetMapping(value = "/queryInstitute")
    @ApiOperation(value = "query_institute")
    public CommonResult queryInstitute() {
        return instituteService.queryInstiture();
    }

    @PostMapping(value = "/insertInstitute")
    @ApiOperation(value = "insert_institute")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertInstitute(@RequestBody @Valid InstituteDTO instituteDTO) {
        return instituteService.insertInstiture(instituteDTO);
    }

    @PostMapping(value = "/queryInstitutePage")
    @ApiOperation(value = "query_Institute_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryInstitutePage(@RequestBody @Valid PageDTO pageDTO){
        return instituteService.queryInstitutePage(pageDTO);
    }

    @PostMapping(value = "/countStudentNum")
    @ApiOperation(value = "count_student_num")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult countStudentNum(@RequestBody String instituteName){
        return instituteService.countStudentNum(instituteName);
    }

    @PostMapping(value = "/countProfessionNum")
    @ApiOperation(value = "count_profession_num")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult countProfessionNum(@RequestBody String instituteName){
        return instituteService.countProfessionNum(instituteName);
    }

    @PostMapping(value = "/countCourseNum")
    @ApiOperation(value = "count_course_num")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult countCourseNum(@RequestBody String instituteName){
        return instituteService.countCourseNum(instituteName);
    }

    @PostMapping(value = "deleteInstitute")
    @ApiOperation(value = "delete_institute")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteInstitute(@RequestBody String instituteName){
        return instituteService.deleteInstitute(instituteName);
    }

    @PostMapping(value = "updateInstitute")
    @ApiOperation(value = "update_institute")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult updateInstitute(@RequestBody @Valid InstituteDTO instituteDTO){
        return instituteService.updateInstitute(instituteDTO);
    }
}
