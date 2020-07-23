package cn.redarm.studentscoremanager.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.TeacherDTO;
import cn.redarm.studentscoremanager.model.DTO.TeacherPageDTO;
import cn.redarm.studentscoremanager.model.entity.Institute;
import cn.redarm.studentscoremanager.model.entity.Teacher;
import cn.redarm.studentscoremanager.repository.CourseRepository;
import cn.redarm.studentscoremanager.repository.InstituteRepository;
import cn.redarm.studentscoremanager.repository.TeacherCourseRepository;
import cn.redarm.studentscoremanager.repository.TeacherRepository;
import cn.redarm.studentscoremanager.service.TeacherService;
import cn.redarm.studentscoremanager.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author redarm
 * @Date 2020/6/24 8:35 下午
 **/
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * @Author Redarm
     * @Description //TODO insert s teacher
     * @Date 12:09 下午 2020/7/3
     * @Param [teacherDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult insertTeacher(TeacherDTO teacherDTO) {
        String instituteName = teacherDTO.getInstituteName();

        if (!StringUtils.isEmpty(instituteName)) {
            instituteName = instituteName.substring(2, instituteName.length() - 2);
        }

        Institute institute = instituteRepository.findByName(instituteName);

        if (institute == null) {
            return CommonResult.failed("There is not a institute named " + teacherDTO.getInstituteName());
        }

        if (teacherRepository.findByNameOrNumber(teacherDTO.getName(), teacherDTO.getNumber()) != null) {
            return CommonResult.failed("There is already have a teacher named " + teacherDTO.getName());
        }

        Teacher teacher = new Teacher();

        teacher.setName(teacherDTO.getName());
        teacher.setSex(teacherDTO.getSex());
        teacher.setNumber(teacherDTO.getNumber());
        teacher.setBirthday(teacherDTO.getBirthday().substring(0, 10));
        teacher.setMoney(teacherDTO.getMoney());
        teacher.setInstitute(institute);
        teacher.setTime(teacherDTO.getTime().substring(0, 10));

        teacherRepository.save(teacher);

        return CommonResult.success(teacher);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query all teachers name
     * @Date 3:44 下午 2020/6/25
     * @Param []
     **/
    @Override
    public CommonResult queryTeacherName() {
        List<Teacher> teachers = teacherRepository.findAll();

        if (teachers.isEmpty()) {
            return CommonResult.failed();
        }

        List<Map> teacherNames = teachers.stream()
                .map(it -> {
                    HashMap<String, String> map = new HashMap<>();

                    map.put("label", it.getName());
                    map.put("value", it.getName());

                    return map;
                }).collect(Collectors.toList());

        return CommonResult.success(teacherNames);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query teacher page by teacher name and institute name
     * @Date 10:44 上午 2020/6/27
     * @Param [teacherPageDTO]
     **/
    @Override
    public CommonResult queryTeacherPage(TeacherPageDTO teacherPageDTO) {
        Integer page = Integer.parseInt(teacherPageDTO.getPage());
        Integer size = Integer.parseInt(teacherPageDTO.getSize());
        String teacherName = teacherPageDTO.getTeacherName();
        String instituteName = teacherPageDTO.getInstituteName();

        log.debug("teacher name: {}, institute name: {}, page: {}, size: {}", teacherName, instituteName, page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = null;

        if (!StringUtils.isEmpty(instituteName)) {
            instituteName = instituteName.substring(2, instituteName.length() - 2);
        }

        if (StringUtils.isEmpty(teacherName) && StringUtils.isEmpty(instituteName)) {
            teacherPage = teacherRepository.findAll(pageable);
            log.debug("teacher page: {}", teacherPageDTO);
            log.debug("teacher : {}",teacherPage);
            return CommonResult.success(teacherPage);
        }

        if (!StringUtils.isEmpty(teacherName) && StringUtils.isEmpty(instituteName)) {
            if (StringUtil.isNumber(teacherName)) {
                teacherPage = teacherRepository.findAllByNumber(teacherName, pageable);
            } else {
                teacherPage = teacherRepository.findAllByNameLike(teacherName, pageable);
            }

            return CommonResult.success(teacherPage);
        }

        if (StringUtils.isEmpty(teacherName) && !StringUtils.isEmpty(instituteName)) {
            Institute institute = instituteRepository.findByName(instituteName);

            if (institute == null) {
                return CommonResult.failed("There is not a institute named: " + instituteName);
            }

            teacherPage = teacherRepository.findAllByInstitute(institute, pageable);

            return CommonResult.success(teacherPage);
        }

        if (!StringUtils.isEmpty(teacherName) && !StringUtils.isEmpty(instituteName)) {
            Institute institute = instituteRepository.findByName(instituteName);

            if (institute == null) {
                return CommonResult.failed("There is not a institute named: " + instituteName);
            }

            if (StringUtil.isNumber(teacherName)) {
                teacherPage = teacherRepository.findAllByNumberAndInstitute(teacherName, institute, pageable);
            } else {
                teacherPage = teacherRepository.findAllByNameLikeAndInstitute(teacherName, institute, pageable);
            }

            return CommonResult.success(teacherPage);
        }

        return CommonResult.failed("Query teachers failed");
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO delete teacher by teacher name
     * @Date 2:09 下午 2020/6/27
     * @Param [name]
     **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteTeacher(String name) {
        JSON json = JSONUtil.parse(name);

        String nam = (String) json.getByPath("name");

        if (StringUtils.isEmpty(nam)) {
            return CommonResult.failed("Teacher name can't be null");
        }

        Teacher teacher = teacherRepository.findByName(name);

        if (teacher == null){
            return CommonResult.success("can't find teacher");
        }

        Integer num = teacherRepository.deleteByName(nam);

        if (num == 1) {
            return CommonResult.success("delete success");
        } else {
            return CommonResult.failed("delete error");
        }
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO update teacher
     * @Date 2:56 下午 2020/6/27
     * @Param [teacherDTO]
     **/
    @Override
    public CommonResult updateTeacher(TeacherDTO teacherDTO) {
        Institute institute = instituteRepository.findByName(teacherDTO.getInstituteName());

        if (institute == null) {
            return CommonResult.failed();
        }

        Teacher teacher = teacherRepository.findByName(teacherDTO.getName());

        if (teacher == null) {
            return CommonResult.failed();
        }

        teacher.setSex(teacherDTO.getSex());
        teacher.setNumber(teacherDTO.getNumber());
        teacher.setBirthday(teacherDTO.getBirthday());
        teacher.setMoney(teacherDTO.getMoney());
        teacher.setInstitute(institute);
        teacher.setTime(teacherDTO.getTime());

        teacherRepository.save(teacher);

        return CommonResult.success("update success");
    }

}
