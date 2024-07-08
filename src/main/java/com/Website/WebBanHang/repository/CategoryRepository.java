package com.Website.WebBanHang.repository;

//import com.Website.WebBanHang.model.Category;
//import com.Website.WebBanHang.model.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//@Repository
//public interface CategoryRepository extends JpaRepository<Category, Long> {
//}
import com.Website.WebBanHang.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByLink(String link);
}