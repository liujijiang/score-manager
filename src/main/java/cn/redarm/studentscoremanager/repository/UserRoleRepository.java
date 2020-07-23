package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.Role;
import cn.redarm.studentscoremanager.model.entity.User;
import cn.redarm.studentscoremanager.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findAllByUser(User user);

    List<UserRole> findAllByRole(Role role);

    Integer deleteByUser(User user);

    UserRole findByUserAndRole(User user, Role role);
}
