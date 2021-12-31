package com.example.webX.backend.TechnicalAssignment.auth.model.repositories;



import com.example.webX.backend.TechnicalAssignment.auth.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query(value = "SELECT id FROM users WHERE username = ?1", nativeQuery = true)
    Optional<String> findAuthIdByUserName(String username);
}
