package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Course;
import cn.redarm.studentscoremanager.model.entity.Institute;
import cn.redarm.studentscoremanager.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Page<Course> findAllByNameLike(String name, Pageable pageable);

    Page<Course> findAllByNumber(String number, Pageable pageable);

    Page<Course> findAllByInstitute(Institute institute, Pageable pageable);

    Page<Course> findAllByNameLikeAndInstitute(String name, Institute institute, Pageable pageable);

    Page<Course> findAllByNumberAndInstitute(String number, Institute institute, Pageable pageable);

    Course findByName(String name);

    Long countAllByInstitute(Institute institute);

    List<Course> findAllByInstitute(Institute institute);

    Integer deleteAllByInstitute(Institute institute);

}
