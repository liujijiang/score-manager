package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseDTO;
import cn.redarm.studentscoremanager.model.DTO.CoursePageDTO;
import cn.redarm.studentscoremanager.model.DTO.DeleteCourseDTO;
import cn.redarm.studentscoremanager.model.DTO.PageDTO;
import cn.redarm.studentscoremanager.model.entity.*;
import cn.redarm.studentscoremanager.repository.*;
import cn.redarm.studentscoremanager.service.CourseService;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author redarm
 * @Date 2020/6/24 4:53 下午
 **/
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query course names
     * @Date 4:59 下午 2020/6/24
     * @Param []
     **/
    @Override
    public CommonResult queryCourse() {
        List<Course> courses = null;
        List<String> courseNames = null;
        Map<String, String> subMap = null;
        List<Map> reList = new ArrayList<>();

        try {
            courses = courseRepository.findAll();

            courseNames = courses.stream()
                    .map(it -> it.getName())
                    .collect(Collectors.toList());

            for (String name : courseNames) {
                subMap = new HashMap<>();

                subMap.put("value", name);
                subMap.put("label", name);

                reList.add(subMap);
            }
        } catch (Exception e) {
            log.error("query course error");
            return CommonResult.failed();
        }

        return CommonResult.success(reList);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO insert a course
     * @Date 6:08 下午 2020/6/24
     * @Param [courseDTO]
     **/
    @Override
    @Transactional
    public CommonResult insertCourse(CourseDTO courseDTO) {
        String teacherName = courseDTO.getTeacherName();

        if (!StringUtils.isEmpty(teacherName)) {
            teacherName = teacherName.substring(2, teacherName.length() - 2);
        }

        Teacher teacher = teacherRepository.findByName(teacherName);

        if (teacher == null) {
            log.debug("insert course but teacher name can't be found, teacher name: {}", teacherName);
            return CommonResult.failed("can't find the teacher name");
        }

        if (courseRepository.findByName(courseDTO.getName()) != null){
            return CommonResult.failed("This course already exists");
        }

        String instituteName = courseDTO.getInstituteName();

        if (StringUtils.isEmpty(instituteName)){
            return CommonResult.failed("institute name is null");
        }

        instituteName = instituteName.substring(2, instituteName.length()-2);

        Institute institute = instituteRepository.findByName(instituteName);

        if (institute == null) {
            log.debug("insert course but institute name can't be found, institute name: {}", courseDTO.getInstituteName());
            return CommonResult.failed("Institute can't find by institute name");
        }
        String categoryName = "";
        if (!StringUtils.isEmpty(courseDTO.getCategoryName())){
            categoryName = StringUtil.removeSQ(courseDTO.getCategoryName());
        }

        CourseCategory courseCategory = courseCategoryRepository.findByName(categoryName);

        if (courseCategory == null) {
            return CommonResult.failed("category name can't be found");
        }

        Course course = new Course();

        course.setName(courseDTO.getName());
        course.setNumber(courseDTO.getNumber());
        course.setInstitute(institute);
        course.setTotalCredits(courseDTO.getTotalCredits());
        course.setCategory(courseCategory);
        course.setTeachers(null);
        course.setExamMethod(courseDTO.getExamMethod());

        Course course1 = courseRepository.save(course);

        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setTeacher(teacher);
        teacherCourse.setCourse(course1);
        teacherCourseRepository.save(teacherCourse);

        return CommonResult.success("insert success");
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query all courses
     * @Date 8:59 下午 2020/6/24
     * @Param []
     **/
    @Override
    public CommonResult queryCourses(PageDTO pageDTO) {
        Integer page = Integer.parseInt(pageDTO.getPage());
        Integer size = Integer.parseInt(pageDTO.getSize());

        Pageable pageable = PageRequest.of(page, size);

        Page<Course> courses = courseRepository.findAll(pageable);

        return CommonResult.success(courses);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query course page
     * @Date 9:55 上午 2020/6/25
     * @Param [coursePageDTO]
     **/
    @Override
    public CommonResult queryCoursePage(CoursePageDTO coursePageDTO) {
        Integer page = Integer.parseInt(coursePageDTO.getPage());
        Integer size = Integer.parseInt(coursePageDTO.getSize());
        String courseName = coursePageDTO.getCourseName();
        String instituteName = coursePageDTO.getInstituteName();
        if (!StringUtils.isEmpty(instituteName)) {
            instituteName = instituteName.substring(2, instituteName.length() - 2);
        }
        log.debug("institute name {}", instituteName);

        Pageable pageable = PageRequest.of(page, size);

        if (courseName.isEmpty() && instituteName.isEmpty()) {
            Page<Course> courses = courseRepository.findAll(pageable);
            return CommonResult.success(courses);
        }

        if (!courseName.isEmpty() && instituteName.isEmpty()) {
            if (StringUtil.isNumber(courseName)) {
                Page<Course> coursePage = courseRepository.findAllByNumber(courseName, pageable);
                return CommonResult.success(coursePage);
            } else {
                Page<Course> coursePage = courseRepository.findAllByNameLike(courseName, pageable);
                return CommonResult.success(coursePage);
            }
        }

        if (StringUtils.isEmpty(courseName) && !StringUtils.isEmpty(instituteName)) {
            Institute institute = instituteRepository.findByName(instituteName);
            if (institute == null) {
                log.debug("Can't find a institute named {}", instituteName);
                return CommonResult.failed("There isn't a institute called " + instituteName);
            }
            Page<Course> coursePage = courseRepository.findAllByInstitute(institute, pageable);
            return CommonResult.success(coursePage);
        }

        if (!StringUtils.isEmpty(courseName) && !StringUtils.isEmpty(instituteName)) {
            Institute institute = instituteRepository.findByName(instituteName);

            if (institute == null) {
                log.debug("Can't find a institute named {}", instituteName);
                return CommonResult.failed("There isn't a institute called " + instituteName);
            }

            if (!StringUtil.isNumber(courseName)) {
                Page<Course> coursePage = courseRepository.findAllByNameLikeAndInstitute(courseName, institute, pageable);
                return CommonResult.success(coursePage);
            } else {
                Page<Course> coursePage = courseRepository.findAllByNumberAndInstitute(courseName, institute, pageable);
                return CommonResult.success(coursePage);
            }
        }

        return CommonResult.failed();
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO delete course by course name
     * @Date 8:18 下午 2020/6/25
     * @Param [deleteCourseDTO]
     **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteCourse(DeleteCourseDTO deleteCourseDTO) {
        Course course = courseRepository.findByName(deleteCourseDTO.getName());

        if (course == null) {
            log.debug("delete course failed, course can't be find, course name: {}", deleteCourseDTO.getName());
            return CommonResult.failed();
        }

        teacherCourseRepository.deleteAllByCourse(course);

        courseRepository.delete(course);

        log.debug("delete course success");
        return CommonResult.success("delete success");
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO update course
     * @Date 2:53 下午 2020/6/26
     * @Param [courseDTO]
     **/
    @Override
    @Modifying
    @Transactional
    public CommonResult updateCourse(CourseDTO courseDTO) {
        Course course = courseRepository.findByName(courseDTO.getName());

        teacherCourseRepository.deleteAllByCourse(course);

        if (course == null) {
            return CommonResult.failed("Find course by course name failed");
        }

        course.setNumber(courseDTO.getNumber());
        course.setExamMethod(courseDTO.getExamMethod());
        course.setTotalCredits(courseDTO.getTotalCredits());

        Institute institute = instituteRepository.findByName(courseDTO.getInstituteName());

        if (institute == null) {
            return CommonResult.failed("Find institute by institute failed");
        }

        course.setInstitute(institute);

        CourseCategory courseCategory = courseCategoryRepository.findByName(courseDTO.getCategoryName());

        if (courseCategory == null) {
            return CommonResult.failed("Find course category by name failed");
        }

        course.setCategory(courseCategory);

        String teacherName = courseDTO.getTeacherName();

        log.debug("teacher name: {}", teacherName);

        if (teacherName != null) {
            teacherName = teacherName.substring(2, teacherName.length() - 2);
        }

        log.debug("teacher name: {}", teacherName);

        Teacher teacher = teacherRepository.findByName(teacherName);

        if (teacher == null) {
            return CommonResult.failed("Find teacher by teacher name failed");
        }

        course.setTeachers(null);

        Course course1 = courseRepository.save(course);

        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourse(course1);
        teacherCourse.setTeacher(teacher);

        teacherCourseRepository.save(teacherCourse);

        return CommonResult.success(course);
    }
}
