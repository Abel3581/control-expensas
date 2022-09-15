package com.control.expensas.repository;

import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByRoleName(RoleEnum roleEnum);
}
