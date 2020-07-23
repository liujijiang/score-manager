package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.*;
import cn.redarm.studentscoremanager.model.VO.UserVO;
import cn.redarm.studentscoremanager.model.entity.Role;
import cn.redarm.studentscoremanager.model.entity.User;
import cn.redarm.studentscoremanager.model.entity.UserRole;
import cn.redarm.studentscoremanager.repository.RoleRepository;
import cn.redarm.studentscoremanager.repository.UserRepository;
import cn.redarm.studentscoremanager.repository.UserRoleRepository;
import cn.redarm.studentscoremanager.service.UserService;
import cn.redarm.studentscoremanager.util.JwtUtil;
import cn.redarm.studentscoremanager.util.MailUtil;
import cn.redarm.studentscoremanager.util.RedisUtil;
import cn.redarm.studentscoremanager.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author redarm
 * @Date 2020/6/19 4:03 下午
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult<java.lang.String>
     * @Author redarm
     * @Description //TODO login
     * @Date 4:19 下午 2020/6/19
     * @Param [loginDTO]
     **/
    @Override
    public CommonResult login(LoginDTO loginDTO) {
        String token = null;
        Map<String, Object> map = new HashMap<>();

        try {
            User user = userRepository.findByUsername(loginDTO.getUsername());

            if (user == null) {
                return CommonResult.failed("username or password wrong");
            }

            if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                return CommonResult.failed("username or password wrong");
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            token = jwtUtil.createToken(user);
            map.put("username", user.getUsername());
            map.put("token", token);
        } catch (Exception e) {
            log.warn("user: {} login error " + e, loginDTO.getUsername());
        }

        return CommonResult.success(map);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult<cn.redarm.studentscoremanager.model.entity.User>
     * @Author redarm
     * @Description //TODO register
     * @Date 4:19 下午 2020/6/19
     * @Param [registerDTO]
     **/
    @Override
    @Transactional
    @Modifying
    public CommonResult<User> register(RegisterDTO registerDTO) {
        User user = new User();
        try {

            if (userRepository.findByUsername(registerDTO.getUsername()) != null) {
                return CommonResult.failed("username has been register");
            }

            if (userRepository.findByEmail(registerDTO.getEmail()) != null){
                return CommonResult.failed("email already has been registered");
            }

            user.setUsername(registerDTO.getUsername());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setEmail(registerDTO.getEmail());

            user.setNickName(registerDTO.getNickName());

            user.setRoleNames("-USER");

            userRepository.save(user);
            userRepository.flush();

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            Role role = roleRepository.findByName("USER");
            if (role == null){
                return CommonResult.failed("There haven't a role called USER, you should add a role called USER first");
            }
            userRole.setRole(role);

            userRoleRepository.save(userRole);

        } catch (Exception e) {
            log.warn("user: {} register error" + e, registerDTO.getUsername());

            return CommonResult.failed("register failed");
        }

        return CommonResult.success(user);
    }

    /**
     * @Author Redarm
     * @Description //TODO query all users by username or role and return by page
     * @Date 9:56 下午 2020/7/1
     * @Param [userPageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryUserPage(UserPageDTO userPageDTO) {
        Integer page = Integer.parseInt(userPageDTO.getPage());
        Integer size = Integer.parseInt(userPageDTO.getSize());

        String username = userPageDTO.getUsername();
        String roleName = userPageDTO.getRoleName();

        if (!StringUtils.isEmpty(roleName)){
            roleName = roleName.substring(2, roleName.length()-2);
        }

        log.debug(roleName);

        Pageable pageable = PageRequest.of(page, size);

        List<User> userList = null;

        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(roleName)){
            userList = userRepository.findAll();
            return CommonResult.success(this.getResult(userList, pageable));
        }

        if (!StringUtils.isEmpty(username)){
            userList = userRepository.findAllByUsername(username);

            return CommonResult.success(this.getResult(userList, pageable));
        }

        if (StringUtils.isEmpty(username) && !StringUtils.isEmpty(roleName)){
            Role role = roleRepository.findByName(roleName);
            List<UserRole> list = userRoleRepository.findAllByRole(role);
            userList = list.stream().map(it -> it.getUser()).collect(Collectors.toList());
            return CommonResult.success(this.getResult(userList, pageable));
        }
        return CommonResult.failed("query error");
    }

    /**
     * @Author Redarm
     * @Description //TODO get a result map from list and page
     * @Date 10:08 上午 2020/7/7
     * @Param [userList, pageable]
     * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    private Map<String, Object> getResult(List<User> userList, Pageable pageable){
        Map<String, Object> result = new HashMap<>();

        List<UserVO> userVOList = userList.stream()
                .map(it -> {
                    List<UserRole> userRoles = userRoleRepository.findAllByUser(it);

                    StringBuilder roleNames = new StringBuilder();

                    log.debug("user roles: " + userRoles.toString());

                    userRoles.stream().map(its -> roleNames.append(its.getRole().getName()) + ", ").collect(Collectors.toList());

                    return new UserVO(it, roleNames.toString());
                }).collect(Collectors.toList());

        userVOList = userVOList.stream()
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        result.put("content", userVOList);
        result.put("total", userVOList.size());

        return result;
    }

    /**
     * @Author Redarm
     * @Description //TODO update user implement
     * @Date 10:08 上午 2020/7/7
     * @Param
     * @return
    **/
    @Override
    public CommonResult updateUser(RegisterDTO registerDTO) {
        User user = userRepository.findByUsername(registerDTO.getUsername());

        if (user == null){
            return CommonResult.failed("can't find a user by this username");
        }

        user.setEmail(registerDTO.getEmail());
        user.setNickName(registerDTO.getNickName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        userRepository.save(user);

        return CommonResult.success("update success");
    }

    /**
     * @Author Redarm
     * @Description //TODO delete user implement
     * @Date 10:19 上午 2020/7/7
     * @Param [userDeleteDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteUser(UserDeleteDTO userDeleteDTO) {
        User user = userRepository.findByUsername(userDeleteDTO.getUsername());
        userRoleRepository.deleteByUser(user);
        Integer num = 0;

        num = userRepository.deleteByUsername(userDeleteDTO.getUsername());

        if (num == 0) {
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO add role implement
     * @Date 10:48 上午 2020/7/7
     * @Param [userAddRoleDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult addRole(UserAddRoleDTO userAddRoleDTO) {
        User user = userRepository.findByUsername(userAddRoleDTO.getUsername());
        Role role = roleRepository.findByName(StringUtil.removeSQ(userAddRoleDTO.getRoleName()));

        if (user == null || role == null){
            return CommonResult.failed("user or role is null");
        }

        if (userRoleRepository.findByUserAndRole(user, role) != null){
            return CommonResult.failed("this user already have this role");
        }

        user.setRoleNames(user.getRoleNames() + "-" + StringUtil.removeSQ(userAddRoleDTO.getRoleName()));
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepository.save(userRole);

        return CommonResult.success("add role success");
    }

    /**
     * @Author Redarm
     * @Description //TODO send mail implement
     * @Date 12:33 下午 2020/7/7
     * @Param [mailDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult sendEmail(MailDTO mailDTO) {

        if (!StringUtils.isEmpty(redisUtil.get(mailDTO.getMailAddress()))){
            return CommonResult.failed("wait 2 minutes late please");
        }

        String msg = mailUtil.sendMessage(mailDTO.getMailAddress(), "来自SDUT-学生成绩管理系统的邮件： " + mailDTO.getMsg());

        redisUtil.add(mailDTO.getMailAddress(),"send");

        if (msg == null){
            return CommonResult.failed("send error");
        }

        return CommonResult.success("send success");
    }
}
