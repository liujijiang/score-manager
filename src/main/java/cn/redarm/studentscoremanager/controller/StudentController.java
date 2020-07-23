package cn.redarm.studentscoremanager.controller;

import cn.gjing.tools.excel.ExcelFactory;
import cn.gjing.tools.excel.write.BigTitle;
import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.StudentDTO;
import cn.redarm.studentscoremanager.model.DTO.StudentPageDTO;
import cn.redarm.studentscoremanager.model.excel.ExcelStudent;
import cn.redarm.studentscoremanager.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/2 8:40 下午
 **/
@RestController
@RequestMapping(value = "/api/student")
@Api(value = "student_controller")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/insertStudent")
    @ApiOperation(value = "insert_student")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult insertStudent(@RequestBody @Valid StudentDTO studentDTO){
        return studentService.insertStudent(studentDTO);
    }

    @PostMapping(value = "/queryStudentPage")
    @ApiOperation(value = "query_student_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryStudentPage(@RequestBody @Valid StudentPageDTO studentPageDTO){
        return studentService.queryStudentPage(studentPageDTO);
    }

    @PostMapping(value = "/deleteStudent")
    @ApiOperation(value = "delete_student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteStudent(@RequestBody String name){
        return studentService.deleteStudent(name);
    }

    @PostMapping(value = "/updateStudent")
    @ApiOperation(value = "update_student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult updateStudent(@RequestBody @Valid StudentDTO studentDTO){
        return studentService.updateStudent(studentDTO);
    }

    @GetMapping(value = "queryStudentNames")
    @ApiOperation(value = "query_student_names")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryStudentNames(){
        return studentService.queryStudentNames();
    }

    @GetMapping(value = "getExcel")
    @ApiOperation(value = "get_excel")
    public void export(HttpServletResponse response){
        ExcelFactory.createWriter(ExcelStudent.class, response)
                .writeTitle(new BigTitle("学生信息表"))
                .write(studentService.getExcel())
                .flush();
    }
}
