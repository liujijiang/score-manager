package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseNatureDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.entity.CourseNature;
import cn.redarm.studentscoremanager.repository.CourseNatureRepository;
import cn.redarm.studentscoremanager.service.CourseNatureService;
import cn.redarm.studentscoremanager.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * @Author Redarm
 * @Date 2020/7/5 7:48 下午
 **/
@Service
@Slf4j
public class CourseNatureServiceImpl implements CourseNatureService {
    
    @Autowired
    private CourseNatureRepository courseNatureRepository;
    
    /**
     * @Author Redarm
     * @Description //TODO insert coursenature implement
     * @Date 7:49 下午 2020/7/5
     * @Param [courseNatureDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult insertCourseNature(CourseNatureDTO courseNatureDTO) {
        log.debug("insert coursenature: " + courseNatureDTO.getName());

        if (courseNatureRepository.findByName(courseNatureDTO.getName()) != null){
            return CommonResult.failed("There is already have a course nature called " + courseNatureDTO.getName());
        }

        CourseNature courseNature = new CourseNature();
        courseNature.setName(courseNatureDTO.getName());

        courseNatureRepository.save(courseNature);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query course nature page implement
     * @Date 8:30 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryCourseNaturePage(PageDTO pageDTO) {
        Pageable pageable = PageUtil.getPageable(pageDTO);

        Page<CourseNature> courseNatures = courseNatureRepository.findAll(pageable);

        return CommonResult.success(courseNatures);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete course nature implement
     * @Date 8:41 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteCourseNature(String name) {
        log.debug("delete course nature: " + name);
        if (StringUtils.isEmpty(name)){
            return CommonResult.failed("course nature name can't be null");
        }

        String[] names = name.split(":");

        name = names[1].substring(1, names[1].length()-2);

        log.debug("name: " + name);

        Long num = 0L;

        num = courseNatureRepository.deleteAllByName(name);

        log.debug("num: " + num);

        if (num == 0L){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query course nature names implement
     * @Date 10:32 上午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryCourseNatureNames() {
        List<CourseNature> courseNatures = courseNatureRepository.findAll();

        List<Map<String, Object>> list = courseNatures.stream()
                .map(it -> {
                    Map<String, Object> map = new HashMap<>();

                    map.put("value", it.getName());
                    map.put("label", it.getName());

                    return map;
                }).collect(Collectors.toList());

        return CommonResult.success(list);
    }
}
