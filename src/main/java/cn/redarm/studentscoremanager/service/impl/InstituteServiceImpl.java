package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.InstituteDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.VO.InstituteVO;
import cn.redarm.studentscoremanager.model.entity.*;
import cn.redarm.studentscoremanager.repository.*;
import cn.redarm.studentscoremanager.service.InstituteService;
import cn.redarm.studentscoremanager.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author redarm
 * @Date 2020/6/24 1:32 下午
 **/
@Service
@Slf4j
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query institute impl
     * @Date 10:45 上午 2020/6/24
     * @Param []
     **/
    @Override
    public CommonResult queryInstiture() {
        List<String> names = null;
        Map<String, Object> subMap = null;
        List<Map> reList = new ArrayList<>();

        try {
            List<Institute> institutes = instituteRepository.findAll();

            if (institutes.isEmpty()) {
                return CommonResult.failed("find nothing");
            }

            names = institutes.stream()
                    .map(it -> it.getName())
                    .collect(Collectors.toList());

            for (String name : names) {
                subMap = new HashMap<>();
                subMap.put("value", name);
                subMap.put("label", name);

                reList.add(subMap);
            }

        } catch (Exception e) {
            log.error("get institute error");
            return CommonResult.failed("get institute error");
        }

        return CommonResult.success(reList);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO add a new institute
     * @Date 1:25 下午 2020/6/24
     * @Param [instituteDTO]
     **/
    @Override
    @Transactional
    @Modifying
    public CommonResult insertInstiture(InstituteDTO instituteDTO) {
        if (instituteRepository.findByName(instituteDTO.getInstituteName()) != null){
            return CommonResult.failed("The institute called this name already exist");
        }

        log.debug("insert institute: {}",instituteDTO.toString());

        Institute institute = new Institute();

        institute.setName(instituteDTO.getInstituteName());

        instituteRepository.save(institute);

        String[] professions = instituteDTO.getProfessions().split("，");

        Arrays.asList(professions).stream()
                .map(it -> {
                    if (StringUtils.isEmpty(it)){
                        return CommonResult.failed("Profession name can't be null");
                    }

                    Profession profession = new Profession();
                    profession.setName(it);
                    profession.setInstitute(institute);

                    professionRepository.save(profession);
                    return null;
                }).collect(Collectors.toList());

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query all institutes implement
     * @Date 11:16 下午 2020/7/4
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryInstitutePage(PageDTO pageDTO) {
        Integer page = Integer.parseInt(pageDTO.getPage());
        Integer size = Integer.parseInt(pageDTO.getSize());
        Pageable pageable = PageRequest.of(page, size);

        Page<Institute> institutes = instituteRepository.findAll(pageable);

        long num = instituteRepository.count();

        List<Institute> instituteList = institutes.getContent();

        // 每个元素转换
        List<InstituteVO> instituteVOS = instituteList.stream()
                .map(it -> {
                    InstituteVO instituteVO = new InstituteVO(it);

                    instituteVO.setStudentNum((Long) this.countStudentNum(it.getName()).getData());
                    instituteVO.setProfessionNum((Long)this.countProfessionNum(it.getName()).getData());
                    instituteVO.setCourseNum((Long)this.countCourseNum(it.getName()).getData());

                    return instituteVO;
                }).collect(Collectors.toList());

        log.debug("instituteVOs: " + instituteVOS.toString());
        log.debug("pageSize: {}, pageNumber: {} ",pageable.getPageSize(), pageable.getPageNumber());

        // 分页
//        instituteVOS.stream()
//                .skip(pageable.getPageSize() * pageable.getPageNumber())
//                .limit(pageable.getPageSize())
//                .collect(Collectors.toList());

        log.debug("instituteVOs page: " + instituteVOS.toString());

        Map<String, Object> map = new HashMap<>();
        map.put("content",instituteVOS);
        map.put("total",num);
        log.debug("total : " + instituteList.size());

        return CommonResult.success(map);
    }

    /**
     * @Author Redarm
     * @Description //TODO count student implement
     * @Date 8:39 上午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult countStudentNum(String instituteName) {
        return this.countNumByInstituteName(instituteName, new Student());
    }

    /**
     * @Author Redarm
     * @Description //TODO count profession implement
     * @Date 8:39 上午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult countProfessionNum(String instituteName) {
        return this.countNumByInstituteName(instituteName, new Profession());
    }

    /**
     * @Author Redarm
     * @Description //TODO count course implement
     * @Date 8:40 上午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult countCourseNum(String instituteName) {
        return this.countNumByInstituteName(instituteName, new Course());
    }

    @Override
    public CommonResult countNumByInstituteName(String instituteName, Object o) {
        if (StringUtils.isEmpty(instituteName)){
            return CommonResult.failed("Institute name can't be null");
        }

        Institute institute = instituteRepository.findByName(instituteName);

        if (institute == null){
            return CommonResult.failed("Can't find institute by the name: " + instituteName);
        }

        Long num = -1L;

        if (o instanceof Student){
            num = studentRepository.countAllByInstitute(institute);
        }else if (o instanceof Profession){
            num = professionRepository.countAllByInstitute(institute);
        } else if (o instanceof Course){
            num = courseRepository.countAllByInstitute(institute);
        }

        if (num == -1L){
            return CommonResult.failed("query error");
        }

        return CommonResult.success(num);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete a insitute by institute name inplement
     * @Date 3:24 下午 2020/7/5
     * @Param [instituteName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteInstitute(String instituteName) {
        log.debug("delete institute: {}", instituteName);
        if (StringUtils.isEmpty(instituteName)){
            return CommonResult.failed("institute name can't be null");
        }

        String[] names = instituteName.split(":");

        instituteName = names[1].substring(1, names[1].length()-2);

        log.debug("institute name: {}", instituteName);

        Institute institute = instituteRepository.findByName(instituteName);

        if (institute == null){
            return CommonResult.failed("Can't find institute bu institute name: " + instituteName);
        }

        Long instituteNum = 0L;

        professionRepository.deleteAllByInstitute(institute);
        studentRepository.deleteAllByInstitute(institute);
        courseRepository.deleteAllByInstitute(institute);
        teacherRepository.deleteAllByInstitute(institute);

        instituteNum = instituteRepository.deleteAllByName(instituteName);

        if (instituteNum == 0L){
            return CommonResult.failed("Delete institute error");
        }

        return CommonResult.success("Delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO update institute implement
     * @Date 4:13 下午 2020/7/5
     * @Param [instituteDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult updateInstitute(InstituteDTO instituteDTO) {
        log.debug("update institute: " + instituteDTO.toString());
        Institute institute = instituteRepository.findByName(instituteDTO.getInstituteName());

        if (institute == null){
            return CommonResult.failed("Can't find a institute by name: " + instituteDTO.getInstituteName());
        }

        Long professionNum = 0L;

        professionNum = professionRepository.deleteAllByInstitute(institute);

        if (professionNum == 0L){
            return CommonResult.failed("Delete professions error");
        }

        String[] professions = instituteDTO.getProfessions().split("，");

        Arrays.asList(professions).stream()
                .map(it -> {
                    if (StringUtils.isEmpty(it)){
                        return CommonResult.failed("Profession name can't be null");
                    }

                    Profession profession = new Profession();
                    profession.setName(it);
                    profession.setInstitute(institute);

                    professionRepository.save(profession);
                    return null;
                }).collect(Collectors.toList());

        return CommonResult.success("insert success");
    }
}
