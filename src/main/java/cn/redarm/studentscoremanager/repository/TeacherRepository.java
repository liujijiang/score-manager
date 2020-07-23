package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Institute;
import cn.redarm.studentscoremanager.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByName(String name);

    Page<Teacher> findAllByNameLike(String name, Pageable pageable);

    Page<Teacher> findAllByNumber(String number, Pageable pageable);

    Page<Teacher> findAllByInstitute(Institute institute, Pageable pageable);

    Page<Teacher> findAllByNameLikeAndInstitute(String name, Institute institute, Pageable pageable);

    Page<Teacher> findAllByNumberAndInstitute(String number, Institute institute, Pageable pageable);

    Teacher findByNameOrNumber(String name, String number);

    Integer deleteByName(String name);

    Integer deleteAllByInstitute(Institute institute);

}
