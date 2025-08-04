package com.raksa.app.repository;

import com.raksa.app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);
}
