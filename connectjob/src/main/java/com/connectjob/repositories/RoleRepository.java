package com.connectjob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.connectjob.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByAuthority(String authority);

}
