package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.ScoreNature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreNatureRepository extends JpaRepository<ScoreNature, Integer> {

    ScoreNature findByName(String name);

    Integer deleteAllByName(String name);
}
