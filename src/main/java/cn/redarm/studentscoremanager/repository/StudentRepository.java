package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Institute;
import cn.redarm.studentscoremanager.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author redarm
 * @Date 2020/6/20 6:33 下午
 **/
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Page<Student> findAllByNameAndSex(String name, String sex, Pageable pageable);

    Page<Student> findAllByInstituteAndSex(Institute institute, String sex, Pageable pageable);

    Student findByName(String name);

    Student findByNameOrNumber(String name, String number);

    Page<Student> findAllByIdInAndSex(List<Integer> ids, String sex, Pageable pageable);

    Page<Student> findAllBySex(String sex, Pageable pageable);

    Page<Student> findAllByNameAndInstituteAndSex(String name, Institute institute, String sex, Pageable pageable);

    Page<Student> findAllByNameAndIdInAndSex(String name, List<Integer> ids, String sex, Pageable pageable);

    Page<Student> findAllByInstituteAndIdInAndSex(Institute institute, List<Integer> ids, String sex, Pageable pageable);

    Page<Student> findAllByNameAndInstituteAndIdInAndSex(String name, Institute institute, List<Integer> ids, String sex, Pageable pageable);

    Page<Student> findAllByName(String name, Pageable pageable);

    Page<Student> findAllByInstitute(Institute institute, Pageable pageable);

    Page<Student> findAllByIdIn(List<Integer> ids, Pageable pageable);

    Page<Student> findAllByNameAndInstitute(String name, Institute institute, Pageable pageable);

    Page<Student> findAllByNameAndIdIn(String name, List<Integer> ids, Pageable pageable);

    Page<Student> findAllByInstituteAndIdIn(Institute intitute, List<Integer> ids, Pageable pageable);

    Page<Student> findAllByNameAndInstituteAndIdIn(String name, Institute institute, List<Integer> ids, Pageable pageable);

    Integer deleteByName(String name);

    Long countAllByInstitute(Institute institute);

    Integer deleteAllByInstitute(Institute institute);
}
