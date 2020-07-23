package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.EducationDTO;
import cn.redarm.studentscoremanager.model.DTO.EducationDeleteDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.entity.Education;
import cn.redarm.studentscoremanager.model.entity.Student;
import cn.redarm.studentscoremanager.repository.EducationRepository;
import cn.redarm.studentscoremanager.service.EducationService;
import cn.redarm.studentscoremanager.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Redarm
 * @Date 2020/7/2 8:53 下午
 **/
@Service
@Slf4j
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    /**
     * @Author Redarm
     * @Description //TODO insert a education
     * @Date 8:55 下午 2020/7/2
     * @Param [educationDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult insertEducation(EducationDTO educationDTO) {
        Set<Student> students = null;

        if (educationRepository.findByName(educationDTO.getName()) != null){
            return CommonResult.failed("education has exist");
        }

        Education education = new Education();

        education.setName(educationDTO.getName());
        education.setYear(educationDTO.getTime());
        education.setStudents(null);

        educationRepository.save(education);

        return CommonResult.success("insert a education success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query all education names
     * @Date 9:16 下午 2020/7/2
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryEducationNames() {
        List<Education> educations = educationRepository.findAll();

        Set<Map<String, String>> set = null;

        if (!educations.isEmpty()){
            set = educations.stream()
                    .map(it -> {
                        Map<String, String> map = new HashMap<>();

                        map.put("value",it.getName());
                        map.put("label",it.getName());

                        return map;
                    }).collect(Collectors.toSet());
        }

        return CommonResult.success(set);
    }

    /**
     * @Author Redarm
     * @Description //TODO query implement
     * @Date 7:53 下午 2020/7/7
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryEducationPage(PageDTO pageDTO) {
        Pageable pageable = PageUtil.getPageable(pageDTO);

        Page<Education> educations = educationRepository.findAll(pageable);

        return CommonResult.success(educations);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete implement
     * @Date 8:06 下午 2020/7/7
     * @Param [educationDeleteDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteEducation(EducationDeleteDTO educationDeleteDTO) {
        Integer num = 0;

        num = educationRepository.deleteAllByName(educationDeleteDTO.getName());

        if (num == 0){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }
}
