package com.Website.WebBanHang.repository;

import com.Website.WebBanHang.model.Blog;
import com.Website.WebBanHang.model.Menu;
import com.Website.WebBanHang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByHideTrueOrderByOrderAsc();
}