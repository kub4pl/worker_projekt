package com.example.workerapplication.repository;


import com.example.workerapplication.model.Role;
import com.example.workerapplication.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByType(RoleType type);

    boolean existsByType(RoleType type);
}
