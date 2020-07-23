package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.DTO.RoleDTO;
import cn.redarm.studentscoremanager.model.entity.Role;
import cn.redarm.studentscoremanager.repository.RoleRepository;
import cn.redarm.studentscoremanager.service.RoleService;
import cn.redarm.studentscoremanager.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Redarm
 * @Date 2020/7/1 9:39 下午
 **/
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * @Author Redarm
     * @Description //TODO query all role's name
     * @Date 9:46 下午 2020/7/1
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryAllRoles() {
        List<Role> roles = roleRepository.findAll();
        
        List<Map> reList = new ArrayList<>();

        if (!roles.isEmpty()){
            Set<String> roleNames = roles.stream()
                    .map(it -> it.getName())
                    .collect(Collectors.toSet());

            Map<String, String> roleMap = null;
            
            for (String name : roleNames){
                roleMap = new HashMap<>();

                roleMap.put("label",name);
                roleMap.put("value",name);
                
                reList.add(roleMap);
            }
        }
        
        return CommonResult.success(reList);
    }

    /**
     * @Author Redarm
     * @Description //TODO insert role implement
     * @Date 8:16 上午 2020/7/7
     * @Param [roleDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult insertRole(RoleDTO roleDTO) {
        if (roleRepository.findByName(roleDTO.getName()) != null){
            return CommonResult.failed("there already have this role");
        }

        Role role = new Role();
        role.setName(roleDTO.getName());

        roleRepository.save(role);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query role page implement
     * @Date 8:37 上午 2020/7/7
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryRolePage(PageDTO pageDTO) {
        Pageable pageable = PageUtil.getPageable(pageDTO);

        Page<Role> roles = roleRepository.findAll(pageable);

        return CommonResult.success(roles);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete role by name implement
     * @Date 8:49 上午 2020/7/7
     * @Param [roleDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteRole(RoleDTO roleDTO) {
        Integer num = 0;

        num = roleRepository.deleteAllByName(roleDTO.getName());

        if (num == 0){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }
}
