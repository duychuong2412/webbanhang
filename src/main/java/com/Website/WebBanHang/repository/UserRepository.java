package com.Website.WebBanHang.repository;

import com.Website.WebBanHang.model.Product;
import com.Website.WebBanHang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}