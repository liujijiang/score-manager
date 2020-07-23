package cn.redarm.studentscoremanager.service;

import cn.redarm.studentscoremanager.comm.CommonResult;
import cn.redarm.studentscoremanager.model.DTO.TeacherDTO;
import cn.redarm.studentscoremanager.model.DTO.TeacherPageDTO;
import cn.redarm.studentscoremanager.model.entity.Teacher;

/**
 * @Author redarm
 * @Date 2020/6/24 8:31 下午
 **/
public interface TeacherService {

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO insert a teacher
     * @Date 8:35 下午 2020/6/24
     * @Param [teacherDTO]
     **/
    CommonResult insertTeacher(TeacherDTO teacherDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query all teachers name
     * @Date 3:44 下午 2020/6/25
     * @Param []
     **/
    CommonResult queryTeacherName();

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO query teacher page by name and institute name
     * @Date 1:51 下午 2020/6/27
     * @Param [teacherPageDTO]
     **/
    CommonResult queryTeacherPage(TeacherPageDTO teacherPageDTO);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO delete teacher by teacher name
     * @Date 2:08 下午 2020/6/27
     * @Param [name]
     **/
    CommonResult deleteTeacher(String name);

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO update teacher
     * @Date 3:37 下午 2020/6/27
     * @Param [teacherDTO]
     **/
    CommonResult updateTeacher(TeacherDTO teacherDTO);

}
