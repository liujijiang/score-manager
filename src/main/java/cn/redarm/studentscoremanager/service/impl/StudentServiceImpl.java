package cn.redarm.studentscoremanager.service.impl;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.StudentDTO;
import cn.redarm.studentscoremanager.model.DTO.StudentPageDTO;
import cn.redarm.studentscoremanager.model.entity.*;
import cn.redarm.studentscoremanager.model.excel.ExcelStudent;
import cn.redarm.studentscoremanager.repository.EducationRepository;
import cn.redarm.studentscoremanager.repository.InstituteRepository;
import cn.redarm.studentscoremanager.repository.StudentEducationRepository;
import cn.redarm.studentscoremanager.repository.StudentRepository;
import cn.redarm.studentscoremanager.service.StudentService;
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
 * @Date 2020/7/2 8:32 下午
 **/
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private EducationRepository educationRepository;
    
    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private StudentEducationRepository studentEducationRepository;
    
    /**
     * @Author Redarm
     * @Description //TODO insert a student
     * @Date 8:40 下午 2020/7/2
     * @Param [studentDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Transactional
    public CommonResult insertStudent(StudentDTO studentDTO) {
        log.debug("insert student : {}", studentDTO.toString());
        if (studentRepository.findByNameOrNumber(studentDTO.getName(), studentDTO.getNumber()) != null){
            return CommonResult.failed("The student called this name or number already exist");
        }

        String educationName = studentDTO.getEducationName();

        if (!StringUtils.isEmpty(educationName)){
            educationName = educationName.substring(2,educationName.length()-2);
        }

        Education education = educationRepository.findByName(educationName);

        if (education == null){
            return CommonResult.failed("education can't be find");
        }

        Set<CourseTaken> courseTakens = new HashSet<>();

        String instituteName = studentDTO.getInstituteName();

        if (!StringUtils.isEmpty(instituteName)){
            instituteName = instituteName.substring(2, instituteName.length()-2);
        }

        Institute institute = instituteRepository.findByName(instituteName);

        Student student = new Student();
        
        student.setName(studentDTO.getName());
        student.setNumber(studentDTO.getNumber());
        student.setTime(studentDTO.getTime().substring(0, 10));
        student.setEducations(null);
        student.setCourseTokens(courseTakens);
        student.setSex(studentDTO.getSex());
        student.setBirthday(studentDTO.getBirthday().substring(0, 10));
        student.setNation(studentDTO.getNation());
        student.setBirthPlace(studentDTO.getBirthPlace());
        student.setHometown(studentDTO.getHometown());
        student.setAddress(studentDTO.getAddress());
        student.setInstitute(institute);

        Student s = studentRepository.save(student);

        StudentEducation studentEducation = new StudentEducation();
        studentEducation.setEducation(education);
        studentEducation.setStudent(s);

        studentEducationRepository.save(studentEducation);

        return CommonResult.success("insert success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query student return page
     * @Date 9:29 下午 2020/7/2
     * @Param [studentPageDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryStudentPage(StudentPageDTO studentPageDTO) {
        Integer page = Integer.parseInt(studentPageDTO.getPage());
        Integer size = Integer.parseInt(studentPageDTO.getSize());

        String name = studentPageDTO.getName();
        String instituteName = studentPageDTO.getInstitute();
        String educationName = studentPageDTO.getEducation();
        String sex = studentPageDTO.getSex();

        log.debug("name : {}, institute name: {}, education name: {}, sex: {}",name,instituteName, educationName, sex);

        if ("1".equals(sex)){
            sex = "男";
        } else if ("0".equals(sex)){
            sex = null;
        } else {
            sex = "女";
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = null;

        Boolean nameExist = !StringUtils.isEmpty(name);
        Boolean instituteExist = !StringUtils.isEmpty(instituteName);
        Boolean educationExist = !StringUtils.isEmpty(educationName);

        Institute institute = null;
        if (instituteExist){
            instituteName = instituteName.substring(2, instituteName.length()-2);
            institute = instituteRepository.findByName(instituteName);
            if (institute == null) {
                return CommonResult.failed("institute can't be found");
            }
        }

        Education education = null;
        List<Integer> ids = null;
        if (educationExist){
            educationName = educationName.substring(2, educationName.length()-2);
            education = educationRepository.findByName(educationName);
            if (education == null){
                return CommonResult.failed("Education can't be found by name:" + educationName);
            }
            List<StudentEducation> educations = studentEducationRepository.findAllByEducation(education.getId());
            ids = educations.stream()
                    .map(it -> it.getStudent().getId())
                    .collect(Collectors.toList());
        }

        if (!nameExist && !instituteExist && !educationExist){
            if (sex == null){
                students = studentRepository.findAll(pageable);
            } else {
                students = studentRepository.findAllBySex(sex, pageable);
            }
            return CommonResult.success(students, "success");
        }

        if (nameExist && !instituteExist && !educationExist){
            if (sex == null){
                students = studentRepository.findAllByName(name, pageable);
            } else {
                students = studentRepository.findAllByNameAndSex(name, sex, pageable);
            }
            return CommonResult.success(students);
        }

        if (!nameExist && instituteExist && !educationExist){
            if (sex == null){
                students = studentRepository.findAllByInstitute(institute, pageable);
            } else {
                students = studentRepository.findAllByInstituteAndSex(institute, sex, pageable);
            }
            return CommonResult.success(students);
        }

        if (!nameExist && !instituteExist && educationExist){
            if (sex == null){
                students = studentRepository.findAllByIdIn(ids, pageable);
            } else {
                students = studentRepository.findAllByIdInAndSex(ids, sex, pageable);
            }
            return CommonResult.success(students);
        }

        if (nameExist && instituteExist && !educationExist){
            if (sex == null){
                students = studentRepository.findAllByNameAndInstitute(name, institute, pageable);
            } else {
                students = studentRepository.findAllByNameAndInstituteAndSex(name, institute, sex, pageable);
            }
            return CommonResult.success(students);
        }

        if (nameExist && !instituteExist && educationExist){
            if (sex == null){
                students = studentRepository.findAllByNameAndIdIn(name, ids, pageable);
            } else {
                students = studentRepository.findAllByNameAndIdInAndSex(name, ids, sex, pageable);
            }
            return CommonResult.success(students);
        }

        if (!nameExist && instituteExist && educationExist){
            if (sex == null){
                students = studentRepository.findAllByInstituteAndIdIn(institute, ids, pageable);
            } else {
                students = studentRepository.findAllByInstituteAndIdInAndSex(institute, ids, sex, pageable);
            }
            return CommonResult.success(students);
        }

        if (nameExist && instituteExist && educationExist){
            if (sex == null){
                students = studentRepository.findAllByNameAndInstituteAndIdIn(name, institute, ids, pageable);
            } else {
                students = studentRepository.findAllByNameAndInstituteAndIdInAndSex(name, institute, ids, sex, pageable);
            }
            return CommonResult.success(students);
        }

        return CommonResult.failed("query students error");
    }

    /**
     * @Author Redarm
     * @Description //TODO delete a student by name impl
     * @Date 4:30 下午 2020/7/4
     * @Param [name]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    @Modifying
    @Transactional
    public CommonResult deleteStudent(String name) {
        log.debug("delete student : name :{}", name);
        if (StringUtils.isEmpty(name)){
            return CommonResult.failed("Name can't be null");
        }
        String[] names = name.split(":");

        name = names[1].substring(1,names[1].length()-2);

        Student student = studentRepository.findByName(name);

        studentEducationRepository.deleteAllByStudent(student.getId());

        Integer num = studentRepository.deleteByName(name);

        if (num != 1){
            return CommonResult.failed("delete error");
        }
        return CommonResult.success("delete success");
    }

    /**
     * @Author Redarm
     * @Description //TODO update student
     * @Date 9:11 下午 2020/7/4
     * @Param [studentDTO]
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Modifying
    @Transactional
    @Override
    public CommonResult updateStudent(StudentDTO studentDTO) {
        log.debug("update student: {}", studentDTO.toString());

        Student student = studentRepository.findByName(studentDTO.getName());

        if ( student == null){
            return CommonResult.failed("can't find the student by this name: " + studentDTO.getName());
        }

        String educationName = studentDTO.getEducationName().substring(2, studentDTO.getEducationName().length()-2);
        String instituteName = studentDTO.getInstituteName().substring(2, studentDTO.getInstituteName().length()-2);

        student.setName(studentDTO.getName());
        student.setNumber(studentDTO.getNumber());
        student.setTime(studentDTO.getTime());
        student.setSex(studentDTO.getSex());
        student.setBirthday(studentDTO.getBirthday());
        student.setNation(studentDTO.getNation());
        student.setHometown(studentDTO.getHometown());
        student.setBirthPlace(studentDTO.getBirthPlace());
        student.setAddress(studentDTO.getAddress());

        Institute institute = instituteRepository.findByName(instituteName);

        if (institute == null){
            return CommonResult.failed("can't find institute by institute name: " + instituteName);
        }

        student.setInstitute(institute);

        Education education = educationRepository.findByName(educationName);

        if (education == null){
            return CommonResult.failed("can't find education by name: " + educationName);
        }

        studentRepository.save(student);

        StudentEducation studentEducation = studentEducationRepository.findByStudent(student);
        studentEducation.setEducation(education);

        studentEducationRepository.save(studentEducation);

        return CommonResult.success("update success");
    }

    /**
     * @Author Redarm
     * @Description //TODO query student names implement
     * @Date 1:57 下午 2020/7/6
     * @Param []
     * @return cn.redarm.studentscoremanager.comm.CommonResult
    **/
    @Override
    public CommonResult queryStudentNames() {
        List<Student> students = studentRepository.findAll();

        List<Map<String, Object>> list = students.stream()
                .map(it -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("value", it.getName());
                    map.put("label", it.getName());

                    return map;
                }).collect(Collectors.toList());

        return CommonResult.success(list);
    }

    /**
     * @Author Redarm
     * @Description //TODO return student excel
     * @Date 10:18 上午 2020/7/8
     * @Param []
     * @return java.util.List<cn.redarm.studentscoremanager.model.excel.ExcelStudent>
    **/
    @Override
    public List<ExcelStudent> getExcel() {
        List<Student> students = studentRepository.findAll();
        
        List<ExcelStudent> list = students.stream()
                .map(it -> new ExcelStudent(it))
                .collect(Collectors.toList());
        
        return list;
    }
}
