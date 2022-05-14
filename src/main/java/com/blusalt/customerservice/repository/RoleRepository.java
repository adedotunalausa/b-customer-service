package com.blusalt.customerservice.repository;

import com.blusalt.customerservice.enums.RoleType;
import com.blusalt.customerservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
