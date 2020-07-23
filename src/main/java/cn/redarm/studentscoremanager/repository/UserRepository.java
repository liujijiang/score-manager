package cn.redarm.studentscoremanager.repository;

import cn.redarm.studentscoremanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author redarm
 * @Date 2020/6/19 3:39 下午
 **/
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findAllByUsername(String username);

    Integer deleteByUsername(String username);

    User findByEmail(String email);

}
