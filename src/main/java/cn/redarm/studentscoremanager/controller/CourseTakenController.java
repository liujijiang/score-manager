package cn.redarm.studentscoremanager.controller;

import cn.gjing.tools.excel.ExcelFactory;
import cn.gjing.tools.excel.write.BigTitle;
import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDataDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDeleteDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenPageDTO;
import cn.redarm.studentscoremanager.model.excel.ExcelCourseTaken;
import cn.redarm.studentscoremanager.service.CourseTakenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/6 9:20 上午
 **/
@RestController
@RequestMapping(value = "/api/courseTaken")
@Api(value = "course_taken_controller")
public class CourseTakenController {

    @Autowired
    private CourseTakenService courseTakenService;

    @PostMapping(value = "insertCourseTaken")
    @ApiOperation(value = "insert_course_taken")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertCourseTaken(@RequestBody @Valid CourseTakenDTO courseTakenDTO){
        return courseTakenService.insertCourseTaken(courseTakenDTO);
    }

    @PostMapping(value = "queryCourseTakenPage")
    @ApiOperation(value = "query_course_taken_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryCourseTakenPage(@RequestBody @Valid CourseTakenPageDTO courseTakenPageDTO){
        return courseTakenService.queryCourseTakenPage(courseTakenPageDTO);
    }

    @PostMapping(value = "deleteCourseTaken")
    @ApiOperation(value = "delte_course_taken")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteCourseTaken(@RequestBody @Valid CourseTakenDeleteDTO courseTakenDeleteDTO){
        return courseTakenService.deleteCourseTaken(courseTakenDeleteDTO);
    }

    @PostMapping(value = "updateCourseTaken")
    @ApiOperation(value = "update_course_taken")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult updateCourseTaken(@RequestBody @Valid CourseTakenDTO courseTakenDTO){
        return courseTakenService.updateCourseTaken(courseTakenDTO);
    }

    @PostMapping(value = "getData")
    @ApiOperation(value = "get_data_from_course_taken")
    public CommonResult getData(@RequestBody @Valid CourseTakenDataDTO courseTakenDataDTO){
        return courseTakenService.getDataOfCourseTaken(courseTakenDataDTO);
    }

    @GetMapping(value = "getExcel")
    @ApiOperation(value = "get_excel")
    public void export(HttpServletResponse response){
        ExcelFactory.createWriter(ExcelCourseTaken.class, response)
                .writeTitle(new BigTitle("学生成绩信息表"))
                .write(courseTakenService.getExcel())
                .flush();
    }
}
