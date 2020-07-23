package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAllByName(String name);

    Role findByName(String name);

    Integer deleteAllByName(String name);

}
