package com.kat.vaultapp.repository;

import com.kat.vaultapp.entity.role.Role;
import com.kat.vaultapp.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
