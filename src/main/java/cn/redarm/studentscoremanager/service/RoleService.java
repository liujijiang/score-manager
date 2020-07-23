package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.DTO.RoleDTO;
import cn.redarm.studentscoremanager.model.entity.Role;

public interface RoleService {

    /**
     * @Author Redarm
     * @Description //TODO query all user
     * @Date 9:39 下午 2020/7/1
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryAllRoles();

    /**
     * @Author Redarm
     * @Description //TODO
     * @Date 8:15 上午 2020/7/7
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult insertRole(RoleDTO roleDTO);
    
    /**
     * @Author Redarm
     * @Description //TODO query role page
     * @Date 8:36 上午 2020/7/7
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult queryRolePage(PageDTO pageDTO);

    /**
     * @Author Redarm
     * @Description //TODO delete role
     * @Date 8:49 上午 2020/7/7
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    CommonResult deleteRole(RoleDTO roleDTO);
}
