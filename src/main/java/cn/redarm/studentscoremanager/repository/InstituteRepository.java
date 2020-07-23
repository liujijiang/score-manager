package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface InstituteRepository extends JpaRepository<Institute, Integer> {

    Institute findByName(String name);

    @Modifying
    @Transactional
    Long deleteAllByName(String name);

}
