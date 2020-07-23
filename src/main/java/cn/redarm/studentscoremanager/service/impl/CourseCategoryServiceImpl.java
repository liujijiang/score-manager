package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseCategoryDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.entity.CourseCategory;
import cn.redarm.studentscoremanager.repository.CourseCategoryRepository;
import cn.redarm.studentscoremanager.service.CourseCategoryService;
import cn.redarm.studentscoremanager.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Redarm
 * @Date 2020/7/5 10:19 下午
 **/
@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {
    
    @Autowired
    private CourseCategoryRepository categoryRepository;
    
    /**
     * @Author Redarm
     * @Description //TODO insert implement
     * @Date 10:19 下午 2020/7/5
     * @Param [courseCategoryDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult insert(CourseCategoryDTO courseCategoryDTO) {
        if (categoryRepository.findByName(courseCategoryDTO.getName()) != null){
            return CommonResult.failed("There is already have a course category called " + courseCategoryDTO.getName());
        }

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setName(courseCategoryDTO.getName());

        categoryRepository.save(courseCategory);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query implement
     * @Date 10:20 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryCourseCategoryPage(PageDTO pageDTO) {
        Pageable pageable = PageUtil.getPageable(pageDTO);

        Page<CourseCategory> courseCategories = categoryRepository.findAll(pageable);

        return CommonResult.success(courseCategories);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete implement
     * @Date 10:20 下午 2020/7/5
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult delete(String name) {
        if (StringUtils.isEmpty(name)){
            return CommonResult.failed("name can't be null");
        }

        String[] names = name.split(":");

        name = names[1].substring(1, names[1].length() - 2);

        Integer num = 0;

        num = categoryRepository.deleteAllByName(name);

        if (num == 0){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query names implement
     * @Date 10:19 上午 2020/7/8
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryNames() {
        List<CourseCategory> list = categoryRepository.findAll();

        List<Map<String, Object>> reList = new ArrayList<>();

        list.stream().map(it -> {
            Map<String, Object> map = new HashMap<>();

            map.put("value", it.getName());
            map.put("label", it.getName());

            reList.add(map);
            return null;
        }).collect(Collectors.toList());

        return CommonResult.success(reList);
    }
}
