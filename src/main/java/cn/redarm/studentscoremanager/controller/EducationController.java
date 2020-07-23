package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.EducationDTO;
import cn.redarm.studentscoremanager.model.DTO.EducationDeleteDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.service.EducationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/2 8:55 下午
 **/
@RestController
@RequestMapping(value = "/api/education")
@Api(value = "education_controller")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping(value = "/insertEducation")
    @ApiOperation(value = "insert_education")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertEducation(@RequestBody @Valid EducationDTO educationDTO){
        return educationService.insertEducation(educationDTO);
    }

    @GetMapping(value = "/queryEducationNames")
    @ApiOperation(value = "query_education_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryEducationNames(){
        return educationService.queryEducationNames();
    }

    @PostMapping(value = "queryEducationPage")
    @ApiOperation(value = "query_education_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryEducationPage(@RequestBody @Valid PageDTO pageDTO){
        return educationService.queryEducationPage(pageDTO);
    }

    @PostMapping(value = "deleteEducation")
    @ApiOperation(value = "delete_education")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteEducation(@RequestBody @Valid EducationDeleteDTO educationDeleteDTO){
        return educationService.deleteEducation(educationDeleteDTO);
    }
}
