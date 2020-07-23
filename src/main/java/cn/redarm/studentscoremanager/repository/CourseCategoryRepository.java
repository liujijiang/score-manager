package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {

    CourseCategory findByName(String name);

    Integer deleteAllByName(String name);
}
