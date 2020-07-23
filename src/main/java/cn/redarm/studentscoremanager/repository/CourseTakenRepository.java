package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CourseTakenRepository extends JpaRepository<CourseTaken, Integer> {

    CourseTaken findByCourseAndStudentAndScoreNature(Course course, Student student, ScoreNature scoreNature);

    Page<CourseTaken> findAllByOrderByScoreAsc(Pageable pageable);

    Page<CourseTaken> findAllByOrderByScoreDesc(Pageable pageable);

    Page<CourseTaken> findAllByStudentOrderByScoreAsc(Student student, Pageable pageable);

    Page<CourseTaken> findAllByStudentOrderByScoreDesc(Student student, Pageable pageable);

    Page<CourseTaken> findAllByCourseOrderByScoreAsc(Course course, Pageable pageable);

    Page<CourseTaken> findAllByCourseOrderByScoreDesc(Course course, Pageable pageable);

    Page<CourseTaken> findAllByCourseInOrderByScoreAsc(List<Course> courses, Pageable pageable);

    Page<CourseTaken> findAllByCourseInOrderByScoreDesc(List<Course> courses, Pageable pageable);

    Integer deleteByCourseAndStudentAndScoreNature(Course course, Student student, ScoreNature scoreNature);

    Set<CourseTaken> findAllByCourse(Course course);
}
