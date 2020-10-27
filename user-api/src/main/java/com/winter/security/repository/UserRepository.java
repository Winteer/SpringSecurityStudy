package com.winter.security.repository;

import com.winter.security.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {

    List<User> findByName(String name);

    User findByUsername(String username);

}
