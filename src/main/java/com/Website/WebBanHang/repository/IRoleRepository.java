package com.Website.WebBanHang.repository;

import com.Website.WebBanHang.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}