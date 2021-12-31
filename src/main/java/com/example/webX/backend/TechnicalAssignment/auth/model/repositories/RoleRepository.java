package com.example.webX.backend.TechnicalAssignment.auth.model.repositories;



import com.example.webX.backend.TechnicalAssignment.auth.model.domain.Role;
import com.example.webX.backend.TechnicalAssignment.auth.model.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
