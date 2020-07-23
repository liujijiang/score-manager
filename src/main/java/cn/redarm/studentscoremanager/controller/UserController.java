package cn.redarm.studentscoremanager.controller;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.*;
import cn.redarm.studentscoremanager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author redarm
 * @Date 2020/6/19 4:20 下午
 **/
@RestController
@RequestMapping(value = "api/user")
@Api(tags = "user_controller")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "user login")
    public CommonResult login(@RequestBody @Valid LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "user register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult register(@RequestBody @Valid RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping(value = "/queryUserPage")
    @ApiOperation(value = "queryUserPage")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult queryUserPage(@RequestBody @Valid UserPageDTO userPageDTO){
        log.debug(userPageDTO.toString());
        return userService.queryUserPage(userPageDTO);
    }

    @PostMapping(value = "updateUser")
    @ApiOperation(value = "update_user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult updateUser(@RequestBody @Valid RegisterDTO registerDTO){
        return userService.updateUser(registerDTO);
    }

    @PostMapping(value = "deleteUser")
    @ApiOperation(value = "delet_user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult deleteUser(@RequestBody @Valid UserDeleteDTO userDeleteDTO){
        return userService.deleteUser(userDeleteDTO);
    }

    @PostMapping(value = "addRole")
    @ApiOperation(value = "add_role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CommonResult addRole(@RequestBody @Valid UserAddRoleDTO userAddRoleDTO){
        return userService.addRole(userAddRoleDTO);
    }

    @PostMapping(value = "sendEmail")
    @ApiOperation(value = "send_mail")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult sendMail(@RequestBody @Valid MailDTO mailDTO){
        return userService.sendEmail(mailDTO);
    }
}
