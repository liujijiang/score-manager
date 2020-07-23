package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.CourseMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMarkRepository extends JpaRepository<CourseMark, Integer> {

    CourseMark findByName(String name);

    Integer deleteAllByName(String name);
}
