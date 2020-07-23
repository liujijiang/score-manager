package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDataDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenDeleteDTO;
import cn.redarm.studentscoremanager.model.DTO.CourseTakenPageDTO;
import cn.redarm.studentscoremanager.model.entity.*;
import cn.redarm.studentscoremanager.model.excel.ExcelCourseTaken;
import cn.redarm.studentscoremanager.repository.*;
import cn.redarm.studentscoremanager.service.CourseTakenService;
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
 * @Author Redarm
 * @Date 2020/7/6 9:19 上午
 **/
@Service
@Slf4j
public class CourseTakenServiceImpl implements CourseTakenService {

    @Autowired
    private CourseTakenRepository courseTakenRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseNatureRepository courseNatureRepository;

    @Autowired
    private ScoreNatureRepository scoreNatureRepository;

    @Autowired
    private CourseMarkRepository courseMarkRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    /**
     * @Author Redarm
     * @Description //TODO insert implement
     * @Date 9:20 上午 2020/7/6
     * @Param [courseTakenDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult insertCourseTaken(CourseTakenDTO courseTakenDTO) {
        log.debug("insert course token : " + courseTakenDTO.toString());
        String courseName = StringUtil.removeSQ(courseTakenDTO.getCourseName());
        String courseNatureName = StringUtil.removeSQ(courseTakenDTO.getCourseNatureName());
        String scoreNatureName = StringUtil.removeSQ(courseTakenDTO.getScoreNatureName());
        String courseMarkName = StringUtil.removeSQ(courseTakenDTO.getCourseMarkName());
        String studentName = StringUtil.removeSQ(courseTakenDTO.getStudentName());

        Course course = null;
        CourseNature courseNature = null;
        ScoreNature scoreNature = null;
        CourseMark courseMark = null;
        Student student = null;

        if (!StringUtils.isEmpty(courseName)){
            course = courseRepository.findByName(courseName);
        }

        if (!StringUtils.isEmpty(courseNatureName)){
            courseNature = courseNatureRepository.findByName(courseNatureName);
        }

        if (!StringUtils.isEmpty(scoreNatureName)){
            scoreNature = scoreNatureRepository.findByName(scoreNatureName);
        }

        if (!StringUtils.isEmpty(courseMarkName)){
            courseMark = courseMarkRepository.findByName(courseMarkName);
        }

        if (!StringUtils.isEmpty(studentName)){
            student = studentRepository.findByName(studentName);
        }

        if (courseTakenRepository.findByCourseAndStudentAndScoreNature(course, student, scoreNature) != null){
            return CommonResult.failed(studentName + " has already " + courseNatureName + " this " + courseName);
        }

        double score = Double.parseDouble(courseTakenDTO.getScore());

        String credits = "0";
        String gradePoint = "0";

        if (score >= 60.0){
            double totalCredits = Double.parseDouble(course.getTotalCredits());
            credits = course.getTotalCredits();

            double newGradePoint = 0.0;
            newGradePoint = (score / 100.0) * totalCredits;
            gradePoint = String.format("%.2f", newGradePoint);
        }

        CourseTaken courseTaken = new CourseTaken();
        courseTaken.setCourse(course);
        courseTaken.setScore(courseTakenDTO.getScore());
        courseTaken.setCourseNature(courseNature);
        courseTaken.setCredits(credits);
        courseTaken.setGradePoint(gradePoint);
        courseTaken.setScoreNature(scoreNature);
        courseTaken.setInvalid(courseTakenDTO.getInvalid());
        courseTaken.setCourseMark(courseMark);
        courseTaken.setSchoolYear(courseTakenDTO.getSchoolYear());
        courseTaken.setSemester(courseTakenDTO.getSemester());
        courseTaken.setStudent(student);

        courseTakenRepository.save(courseTaken);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query course taken page implement
     * @Date 3:48 下午 2020/7/6
     * @Param [courseTakenPageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryCourseTakenPage(CourseTakenPageDTO courseTakenPageDTO) {
        Integer page = Integer.parseInt(courseTakenPageDTO.getPage());
        Integer size = Integer.parseInt(courseTakenPageDTO.getSize());

        Pageable pageable = PageRequest.of(page, size);

        String studentName = courseTakenPageDTO.getStudentName();
        String instituteName = StringUtil.removeSQ(courseTakenPageDTO.getInstituteName());
        String courseName = StringUtil.removeSQ(courseTakenPageDTO.getCourseName());
        Integer orderFlag = Integer.parseInt(courseTakenPageDTO.getOrderFlag());

        boolean studentNameExist = !StringUtils.isEmpty(studentName);
        boolean instituteNameExist = !StringUtils.isEmpty(instituteName);
        boolean courseNameExist = !StringUtils.isEmpty(courseName);

        Page<CourseTaken> courseTakens = null;

        if (!studentNameExist && !instituteNameExist && !courseNameExist){
            if (orderFlag == 1){
                courseTakens = courseTakenRepository.findAllByOrderByScoreAsc(pageable);
            } else {
                courseTakens = courseTakenRepository.findAllByOrderByScoreDesc(pageable);
            }

            return CommonResult.success(courseTakens);
        }

        if (studentNameExist){
            Student student = studentRepository.findByName(studentName);

            if (student == null){
                return CommonResult.failed("Can't find a student called " + studentName);
            }

            if (orderFlag == 1){
                courseTakens = courseTakenRepository.findAllByStudentOrderByScoreAsc(student, pageable);
            } else {
                courseTakens = courseTakenRepository.findAllByStudentOrderByScoreDesc(student, pageable);
            }

            return CommonResult.success(courseTakens);
        }

        if (!studentNameExist && instituteNameExist){
            List<Course> courses = this.getCourseListFromInstitute(instituteName);

            if (courses == null){
                return CommonResult.failed("Can't find a institute called " + instituteName);
            }

//            List<CourseTaken> list = new ArrayList<>();
//
//            for (Course course : courses){
//                List<CourseTaken> courseTakenList = courseTakenRepository.findAllByCourse(course);
//
//                for (CourseTaken courseTaken : courseTakenList){
//                    list.add(courseTaken);
//                }
//            }

            if (orderFlag == 1){
                courseTakens = courseTakenRepository.findAllByCourseInOrderByScoreAsc(courses, pageable);
            } else {
                courseTakens = courseTakenRepository.findAllByCourseInOrderByScoreDesc(courses, pageable);
            }

//            list = list.stream().skip(pageable.getPageSize() * pageable.getPageNumber())
//                    .limit(pageable.getPageSize()).collect(Collectors.toList());

            return CommonResult.success(courseTakens);
        }

        if (!studentNameExist && !instituteNameExist && courseNameExist){
            Course course = courseRepository.findByName(courseName);

            if (orderFlag == 1){
                courseTakens = courseTakenRepository.findAllByCourseOrderByScoreAsc(course, pageable);
            } else {
                courseTakens = courseTakenRepository.findAllByCourseOrderByScoreDesc(course, pageable);
            }

            return CommonResult.success(courseTakens);
        }

        return CommonResult.failed("select error");
    }

    private List<Course> getCourseListFromInstitute(String instituteName){
        Institute institute = instituteRepository.findByName(instituteName);

        if (institute == null){
            return null;
        }

        List<Course> courses = courseRepository.findAllByInstitute(institute);

        return courses;
    }

    /**
     * @Author Redarm
     * @Description //TODO delete implement
     * @Date 8:08 下午 2020/7/6
     * @Param [courseName, studentName]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteCourseTaken(CourseTakenDeleteDTO courseTakenDeleteDTO) {
        Course course = courseRepository.findByName(courseTakenDeleteDTO.getCourseName());
        Student student = studentRepository.findByName(courseTakenDeleteDTO.getStudentName());
        ScoreNature scoreNature = scoreNatureRepository.findByName(courseTakenDeleteDTO.getScoreNatureName());

        if (course == null || student == null || scoreNature == null){
            return CommonResult.failed("can't find course or student");
        }

        Integer num = 0;

        num = courseTakenRepository.deleteByCourseAndStudentAndScoreNature(course, student, scoreNature);

        if (num == 0){
            return CommonResult.failed("delete error");
        }

        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO update implement
     * @Date 8:33 下午 2020/7/6
     * @Param [courseTakenDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult updateCourseTaken(CourseTakenDTO courseTakenDTO) {
        String courseName = StringUtil.removeSQ(courseTakenDTO.getCourseName());
        String courseNatureName = StringUtil.removeSQ(courseTakenDTO.getCourseNatureName());
        String scoreNatureName = StringUtil.removeSQ(courseTakenDTO.getScoreNatureName());
        String courseMarkName = StringUtil.removeSQ(courseTakenDTO.getCourseMarkName());
        String studentName = StringUtil.removeSQ(courseTakenDTO.getStudentName());

        log.debug("update course taken : {} , {} , {} , {} , {}", courseName , courseNatureName , scoreNatureName, courseMarkName, studentName);

        Course course = null;
        CourseNature courseNature = null;
        ScoreNature scoreNature = null;
        CourseMark courseMark = null;
        Student student = null;

        if (!StringUtils.isEmpty(courseName)){
            course = courseRepository.findByName(courseName);
        }

        if (!StringUtils.isEmpty(courseNatureName)){
            courseNature = courseNatureRepository.findByName(courseNatureName);
        }

        if (!StringUtils.isEmpty(scoreNatureName)){
            scoreNature = scoreNatureRepository.findByName(scoreNatureName);
        }

        if (!StringUtils.isEmpty(courseMarkName)){
            courseMark = courseMarkRepository.findByName(courseMarkName);
        }

        if (!StringUtils.isEmpty(studentName)){
            student = studentRepository.findByName(studentName);
        }

        CourseTaken courseTaken = courseTakenRepository.findByCourseAndStudentAndScoreNature(course, student, scoreNature);

        if (courseTaken == null){
            return CommonResult.failed("can't find the course taken");
        }

        double score = Double.parseDouble(courseTakenDTO.getScore());

        String credits = "0";
        String gradePoint = "0";

        if (score >= 60.0){
            double totalCredits = Double.parseDouble(course.getTotalCredits());
            credits = course.getTotalCredits();

            double newGradePoint = 0.0;
            newGradePoint = (score / 100.0) * totalCredits;
            gradePoint = String.format("%.2f", newGradePoint);
        }

        courseTaken.setScore(courseTakenDTO.getScore());
        courseTaken.setCredits(credits);
        courseTaken.setGradePoint(gradePoint);
        courseTaken.setScoreNature(scoreNature);
        courseTaken.setInvalid(courseTakenDTO.getInvalid());
        courseTaken.setCourseMark(courseMark);
        courseTaken.setSchoolYear(courseTakenDTO.getSchoolYear());
        courseTaken.setSemester(courseTakenDTO.getSemester());

        courseTakenRepository.save(courseTaken);

        return CommonResult.success("update success");
    }

    /**
     * @Author Redarm
     * @Description //TODO get data from course taken and use for dash board
     * @Date 2:43 下午 2020/7/7
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult getDataOfCourseTaken(CourseTakenDataDTO courseTakenDataDTO) {
        Institute institute = instituteRepository.findByName(StringUtil.removeSQ(courseTakenDataDTO.getInstituteName()));

        if (institute == null){
            return CommonResult.failed("can't find the institute");
        }

        List<Map<String, Object>> list = new ArrayList<>();

        List<Course> courses = courseRepository.findAllByInstitute(institute);

        courses.stream().map(it -> {
            Map<String, Object> map = new HashMap<>();

            map.put("学院名",it.getName());
            map.put("总学分", it.getTotalCredits());

            Set<CourseTaken> courseTakens = courseTakenRepository.findAllByCourse(it);

            double score = 0.0;
            for (CourseTaken courseTaken : courseTakens){
                score += Double.parseDouble(courseTaken.getScore());
            }
            score = score/(courseTakens.size());
            map.put("平均成绩", score);
            list.add(map);
            return null;
        }).collect(Collectors.toList());

        return CommonResult.success(list);
    }

    /**
     * @Author Redarm
     * @Description //TODO get excel implement
     * @Date 10:09 上午 2020/7/8
     * @Param []
     * @return java.util.List<cn.redarm.studentscoremanager.model.excel.ExcelCourseTaken>
    **/
    @Override
    public List<ExcelCourseTaken> getExcel() {
        List<CourseTaken> courseTakens = courseTakenRepository.findAll();

        List<ExcelCourseTaken> list = courseTakens.stream()
                .map(it -> new ExcelCourseTaken(it))
                .collect(Collectors.toList());

        return list;
    }
}
