package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Institute;
import cn.redarm.studentscoremanager.model.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

    Profession findByName(String name);

    Long countAllByInstitute(Institute institute);

    @Modifying
    @Transactional
    Long deleteAllByInstitute(Institute institute);

}
