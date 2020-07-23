package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Course;
import cn.redarm.studentscoremanager.model.entity.Teacher;
import cn.redarm.studentscoremanager.model.entity.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Integer> {

    Integer deleteAllByCourse(Course course);

    Integer deleteAllByTeacher(Teacher teacher);
}
