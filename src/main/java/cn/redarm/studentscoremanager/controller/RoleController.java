package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.DTO.RoleDTO;
import cn.redarm.studentscoremanager.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Redarm
 * @Date 2020/7/1 9:46 下午
 **/
@RestController
@RequestMapping(value = "/api/role")
@Api(value = "role_controller")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/queryAllRoleNames")
    @ApiOperation(value = "queryAllRoleNames")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryAllRoleNames(){
        return roleService.queryAllRoles();
    }

    @PostMapping(value = "/insertRole")
    @ApiOperation(value = "insert_role")
    public CommonResult insertRole(@RequestBody @Valid RoleDTO roleDTO){
        return roleService.insertRole(roleDTO);
    }

    @PostMapping(value = "queryRolePage")
    @ApiOperation(value = "query_role_page")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryRolePage(@RequestBody @Valid PageDTO pageDTO){
        return roleService.queryRolePage(pageDTO);
    }

    @PostMapping(value = "deleteRole")
    @ApiOperation(value = "delete_role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteRole(@RequestBody @Valid RoleDTO roleDTO){
        return roleService.deleteRole(roleDTO);
    }
}
