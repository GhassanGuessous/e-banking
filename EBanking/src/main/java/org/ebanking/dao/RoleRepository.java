package org.ebanking.dao;

import org.ebanking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRole(String role);
}
