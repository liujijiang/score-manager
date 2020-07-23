package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Student;
import cn.redarm.studentscoremanager.model.entity.StudentEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentEducationRepository extends JpaRepository<StudentEducation, Integer> {

    @Query(value = "SELECT * FROM sys_student_education t WHERE t.education_id = ?1", nativeQuery = true)
    List<StudentEducation> findAllByEducation(Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sys_student_education WHERE student_id = ?1", nativeQuery = true)
    void deleteAllByStudent(Integer studentId);

    StudentEducation findByStudent(Student student);

}
