package com.raksa.app.repository;

import com.raksa.app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM USER_ACCOUNT WHERE role <> :role", nativeQuery = true)
    void deleteAllExceptByRole(@Param("role") String role);
}
