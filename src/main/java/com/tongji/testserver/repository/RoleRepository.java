package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {
}
