package org.ebanking.dao;

import org.ebanking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    public Role findRoleByRole(String role);
}
