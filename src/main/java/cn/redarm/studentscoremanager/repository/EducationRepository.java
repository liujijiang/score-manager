package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Integer> {

    Education findByName(String name);

    Integer deleteAllByName(String name);
}
