package com.Website.WebBanHang.repository;

import com.Website.WebBanHang.model.Blog;
import com.Website.WebBanHang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByHideTrueOrderByOrderAsc();
}