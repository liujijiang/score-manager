package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.*;

public interface UserService {

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO user login
     * @Date 4:20 下午 2020/6/19
     * @Param [loginDTO]
     **/
    CommonResult login(LoginDTO loginDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO register a new user
     * @Date 4:20 下午 2020/6/19
     * @Param [registerDTO]
     **/
    CommonResult register(RegisterDTO registerDTO);

    /**
     * @Author Redarm
     * @Description //TODO query all roles and return by page
     * @Date 9:56 下午 2020/7/1
     * @Param [userPageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryUserPage(UserPageDTO userPageDTO);

    /**
     * @Author Redarm
     * @Description //TODO update user 
     * @Date 10:07 上午 2020/7/7
     * @Param [registerDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult updateUser(RegisterDTO registerDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO delete a user by username
     * @Date 10:19 上午 2020/7/7
     * @Param [userDeleteDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteUser(UserDeleteDTO userDeleteDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO add a role for user
     * @Date 11:56 上午 2020/7/7
     * @Param [userAddRoleDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult addRole(UserAddRoleDTO userAddRoleDTO);

    /**
     * @Author Redarm
     * @Description //TODO send a mail to a user
     * @Date 2:43 下午 2020/7/7
     * @Param [mailDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult sendEmail(MailDTO mailDTO);
}
