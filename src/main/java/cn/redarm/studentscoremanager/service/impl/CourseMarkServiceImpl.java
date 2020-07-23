package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseMarkDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.entity.CourseMark;
import cn.redarm.studentscoremanager.repository.CourseMarkRepository;
import cn.redarm.studentscoremanager.service.CourseMarkService;
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
 * @Date 2020/7/5 10:53 下午
 **/
@Service
@Slf4j
public class CourseMarkServiceImpl implements CourseMarkService {

    @Autowired
    private CourseMarkRepository courseMarkRepository;

    /**
     * @Author Redarm
     * @Description //TODO insert implement
     * @Date 10:53 下午 2020/7/5
     * @Param [courseMarkDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult insert(CourseMarkDTO courseMarkDTO) {
        if (courseMarkRepository.findByName(courseMarkDTO.getName()) != null){
            return CommonResult.failed("There already have a course mark called " + courseMarkDTO.getName());
        }

        CourseMark courseMark = new CourseMark();
        courseMark.setName(courseMarkDTO.getName());

        courseMarkRepository.save(courseMark);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query implement
     * @Date 10:54 下午 2020/7/5
     * @Param [pageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryCourseMarkPage(PageDTO pageDTO) {
        Pageable pageable = PageUtil.getPageable(pageDTO);

        Page<CourseMark> courseMarks = courseMarkRepository.findAll(pageable);

        return CommonResult.success(courseMarks);
    }

    /**
     * @Author Redarm
     * @Description //TODO delete implement
     * @Date 10:54 下午 2020/7/5
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

        num = courseMarkRepository.deleteAllByName(name);

        if (num == 0){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query course mark names
     * @Date 11:07 上午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryCourseMarkNames() {
        List<CourseMark> courseMarks = courseMarkRepository.findAll();

        List<Map<String, Object>> list = courseMarks.stream()
                .map(it -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("value", it.getName());
                    map.put("label", it.getName());

                    return map;
                }).collect(Collectors.toList());

        log.debug("query course mark names list: " + list.toString());

        return CommonResult.success(list);
    }
}
