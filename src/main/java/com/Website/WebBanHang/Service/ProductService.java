package com.Website.WebBanHang.Service;
//
//import com.Website.WebBanHang.model.Product;
//import com.Website.WebBanHang.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//import java.util.Optional;
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ProductService {
//    private final ProductRepository productRepository;
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//    public Optional<Product> getProductById(Long id) {
//        return productRepository.findById(id);
//    }
//    public Product addProduct(Product product) {
//        return productRepository.save(product);
//    }
//    public Product updateProduct(Product product) {
//        Product existingProduct = productRepository.findById((long)
//                        product.getId())
//                .orElseThrow(() -> new IllegalStateException("Product with ID " + product.getId() + " does not exist."));
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setNums(product.getNums());
//        existingProduct.setDetail(product.getDetail());
//        existingProduct.setCategory(product.getCategory());
//        existingProduct.setMeta(product.getMeta());
//        existingProduct.setOrder(product.getOrder());
//        existingProduct.setLink(product.getLink());
//        existingProduct.setHide(product.isHide());
//        return productRepository.save(existingProduct);
//    }
//    public void deleteProductById(Long id) {
//        if (!productRepository.existsById(id)) {
//            throw new IllegalStateException("Product with ID " + id + " does not exist.");
//        }
//        productRepository.deleteById(id);
//    }
//}
import com.Website.WebBanHang.model.Category;
import com.Website.WebBanHang.model.Product;
import com.Website.WebBanHang.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    // Các phương thức khác đã được định nghĩa ở trên...


    public Optional<Product> getProductByLink(String link) {
        return productRepository.findByLink(link);
    }
    public List<Product> getProductsByCategoryId(int categoryId) {
        return productRepository.findByCategoryIdAndHideTrueOrderByOrderAsc(categoryId);
    }
    public Map<Category, List<Product>> getTop3ProductsByCategory() {
        List<Product> allProducts = productRepository.findAll();
        Map<Category, List<Product>> productsByCategory = allProducts.stream()
                .filter(Product::isHide)
                .collect(Collectors.groupingBy(Product::getCategory));

        productsByCategory.forEach((category, products) -> {
            List<Product> top3Products = products.stream()
                    .sorted(Comparator.comparingInt(Product::getOrder))
                    .limit(3)
                    .collect(Collectors.toList());
            productsByCategory.put(category, top3Products);
        });

        return productsByCategory;
    }
    public Map<Category, List<Product>> getTop6ProductsByCategory() {
        List<Product> allProducts = productRepository.findAll();
        Map<Category, List<Product>> productsByCategory = allProducts.stream()
                .filter(Product::isHide)
                .collect(Collectors.groupingBy(Product::getCategory));

        productsByCategory.forEach((category, products) -> {
            List<Product> top6Products = products.stream()
                    .sorted(Comparator.comparingInt(Product::getOrder))
                    .limit(6)
                    .collect(Collectors.toList());
            productsByCategory.put(category, top6Products);
        });

        return productsByCategory;
    }

    public void addProduct(@Valid Product product) {

        productRepository.save(product);
    }
//
//    // Phương thức để lưu hình ảnh vào thư mục và trả về đường dẫn
//    private String saveImage(MultipartFile file) {
//        // Thực hiện lưu file vào thư mục và trả về đường dẫn
//        // Ví dụ sử dụng java.nio.file.Files để lưu file
//        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("/path/to/save/directory/" + file.getOriginalFilename());
//            Files.write(path, bytes);
//            return path.toString();
//        } catch (IOException e) {
//            log.error("Failed to save image: {}", e.getMessage());
//            throw new RuntimeException("Failed to save image", e);
//        }
//    }


}
