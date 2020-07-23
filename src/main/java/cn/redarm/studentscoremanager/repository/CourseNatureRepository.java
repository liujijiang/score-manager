package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.CourseNature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseNatureRepository extends JpaRepository<CourseNature, Integer> {

    CourseNature findByName(String name);

    Long deleteAllByName(String name);
}
