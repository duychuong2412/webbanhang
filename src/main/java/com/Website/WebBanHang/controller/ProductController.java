//package com.Website.WebBanHang.controller;
//
//import com.Website.WebBanHang.Service.CategoryService;
//import com.Website.WebBanHang.Service.MenuService;
//import com.Website.WebBanHang.Service.ProductService;
//import com.Website.WebBanHang.model.Category;
//import com.Website.WebBanHang.model.Menu;
//import com.Website.WebBanHang.model.Product;
//import jakarta.validation.constraints.NotNull;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.ui.Model;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.ui.Model;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/product")
//@RequiredArgsConstructor
//@Slf4j
//public class ProductController {
//    @Autowired
//    private final CategoryService categoryService;
//    @Autowired
//    private final MenuService menuService;
//    @Autowired
//    private final ProductService productService;
//
//    public static String UPLOAD_DIRECTORY = "D:\\Java\\WebBanHang\\src\\main\\resources\\static\\User\\img";
//
//    @GetMapping
//    public String showProductList(Model model, Authentication authentication) {
//        List<Menu> menus = menuService.findAllMenu();
//        Map<Category, List<Product>> categoryProducts = productService.getTop6ProductsByCategory();
//        model.addAttribute("categoryProducts", categoryProducts);
//        model.addAttribute("menus", menus);
//
//        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken) {
//            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
//            model.addAttribute("userName", attributes.get("name"));
//            model.addAttribute("userEmail", attributes.get("email"));
//            model.addAttribute("userPicture", attributes.get("picture"));
//        }
//        return "product/category-listproduct";
//    }
//
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("product", new Product());
//        model.addAttribute("categories", categoryService.findAll());
//        return "product/add-product";
//    }
//
//    @PostMapping("/create")
//    public String addProduct(@NotNull @Valid @ModelAttribute Product product,
//                             BindingResult result, Model model,
//                             @RequestParam("img1") MultipartFile file1,
//                             @RequestParam("img2") MultipartFile file2,
//                             @RequestParam("img3") MultipartFile file3) throws IOException {
//        if (result.hasErrors()) {
//            model.addAttribute("categories", categoryService.findAll());
//            return "product/add-product";
//        }
//
//        product.setImg1(uploadImage(file1));
//        product.setImg2(uploadImage(file2));
//        product.setImg3(uploadImage(file3));
//        productService.addProduct(product);
//        return "redirect:/product";
//    }
//
//    public static String uploadImage(MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            return "image empty !!!";
//        }
//
//        try {
//            // Tạo tên ngẫu nhiên cho file
//            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//            // Tạo đường dẫn cho file
//            Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
//            // Ghi file vào đường dẫn
//            Files.write(filePath, file.getBytes());
//
//            // Trả về đường dẫn file đã upload
//            return fileName;
//        } catch (IOException e) {
//            // Xử lý nếu có lỗi khi ghi file
//            e.printStackTrace();
//            return "error while uploading image !!!";
//        }
//    }
//
//    @GetMapping("/detail/{id}")
//    public String productDetail(@PathVariable("id") int id, Model model) {
//        Optional<Product> productOpt = productService.getProductById(id);
//        if (productOpt.isPresent()) {
//            model.addAttribute("product", productOpt.get());
//            addCommonAttributes(model);
//            return "product/productDetail";
//        } else {
//            return "error/404"; // Trả về trang lỗi 404 nếu không tìm thấy sản phẩm
//        }
//    }
//
//    @GetMapping("/{category}")
//    public String ProductCategory(Model model, @PathVariable String category,
//                                  @RequestParam(name = "page", defaultValue = "0") int page) {
//        Category cat = categoryService.findByLink(category);
//        if (cat == null) {
//            return "error"; // Xử lý khi không tìm thấy danh mục
//        }
//        int categoryId = cat.getId();
//        List<Product> productsForCategory = productService.getProductsByCategoryId(categoryId);
//        model.addAttribute("categoryName", cat.getName());
//        model.addAttribute("productsForCategory", productsForCategory);
//        addCommonAttributes(model);
//        return "product/productcategory";
//    }
//
//    private void addCommonAttributes(Model model) {
//        model.addAttribute("categories", categoryService.findAll());
//        model.addAttribute("menus", menuService.findAllMenu());
//    }
//}
//





package com.Website.WebBanHang.controller;

import com.Website.WebBanHang.Service.CategoryService;
import com.Website.WebBanHang.Service.MenuService;
import com.Website.WebBanHang.Service.ProductService;
import com.Website.WebBanHang.model.Category;
import com.Website.WebBanHang.model.Menu;
import com.Website.WebBanHang.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    @Autowired

    private final CategoryService categoryService;
    @Autowired

    private final MenuService menuService;
    @Autowired
    private final ProductService productService;

    public static String UPLOAD_DIRECTORY = "D:\\Java\\WebBanHang\\src\\main\\resources\\static\\User\\img";

    @GetMapping
    public String showProductList(Model model, Authentication authentication) {
        List<Menu> menus = menuService.findAllMenu();
        Map<Category, List<Product>> categoryProducts = productService.getTop6ProductsByCategory();
        model.addAttribute("categoryProducts", categoryProducts);
        model.addAttribute("menus", menus);

        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
            model.addAttribute("userName", attributes.get("name"));
            model.addAttribute("userEmail", attributes.get("email"));
            model.addAttribute("userPicture", attributes.get("picture"));
        }
        return "product/category-listproduct";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/add-product";
    }

    @PostMapping("/create")
    public String addProduct(@NotNull
                             @Valid @ModelAttribute Product product,
                             BindingResult result,
                             @RequestParam("img1") MultipartFile file1,
                             @RequestParam("img2") MultipartFile file2,
                             @RequestParam("img3") MultipartFile file3
    ) throws IOException {
        product.setImg1(uploadImage(file1));
        product.setImg2(uploadImage(file2));
        product.setImg3(uploadImage(file3));
        productService.addProduct(product);
        return "redirect:/product";
    }

    public static String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "image empty !!!";
        }

        try {
            // Tạo tên ngẫu nhiên cho file
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            // Tạo đường dẫn cho file
            Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
            // Ghi file vào đường dẫn
            Files.write(filePath, file.getBytes());

            // Trả về đường dẫn file đã upload
            String fileUrl = fileName;
            return fileUrl;
        } catch (IOException e) {
            // Xử lý nếu có lỗi khi ghi file
            e.printStackTrace();

            // dạng object cần tr về của ckeditor
            // json: { uploaded: true/false, url = `path-to-your-file`}
            return "error while uploading image !!!";
        }
    }
    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable("id") int id, Model model) {
        Optional<Product> productOpt = productService.getProductById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            addCommonAttributes(model);
            return "product/productDetail";
        } else {
            return "error"; // Handle the case when the product is not found
        }
    }
//    @GetMapping("/detail")
//    public String productDetail(@RequestParam("link") String link, Model model) {
//        Optional<Product> productOpt = productService.getProductByLink(link);
//        if (productOpt.isPresent()) {
//            model.addAttribute("product", productOpt.get());
//            addCommonAttributes(model);
//            return "/product/productDetail";
//        } else {
//            return "error"; // Handle the case when the product is not found
//        }
//    }

    @GetMapping("/{category}")
    public String ProductCategory(Model model, @PathVariable String category,
                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        Category cat = categoryService.findByLink(category);
        if (cat == null) {
            return "error"; // Xử lý khi không tìm thấy danh mục
        }
        int categoryId = cat.getId();
        List<Product> productsForCategory =
                productService.getProductsByCategoryId(categoryId);
        model.addAttribute("categoryName", cat.getName());
        model.addAttribute("productsForCategory", productsForCategory);
        addCommonAttributes(model);
        return "product/product-category";
    }

    private void addCommonAttributes(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("menus", menuService.findAllMenu());
    }
}
//
//    @GetMapping("/{category}")
//    public String ProductCategory(Model model, @PathVariable String category) {
//        Category cat = categoryService.findByLink(category);
//        if (cat == null) {
//            return "error"; // Xử lý khi không tìm thấy danh mục
//        }
//        int categoryId = cat.getId();
//        List<Product> productsForCategory = productService.getProductsByCategoryId(categoryId);
//        model.addAttribute("categoryName", cat.getName());
//        model.addAttribute("productsForCategory", productsForCategory);
//        addCommonAttributes(model);
//        return "product/product-category";
//    }
//    private void addCommonAttributes(Model model) {
//        model.addAttribute("product", productService.getAllProducts());
//        model.addAttribute("categories", categoryService.findAll());
//        model.addAttribute("menus", menuService.findAllMenu());
//    }




//    @GetMapping
//    public String showProductList(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
//        return "/product/product-list";
//    }
//    // For adding a new product
//    @GetMapping("/add")
////    @PreAuthorize("hasAuthority('ADMIN')")
//    public String showAddForm(Model model) {
//        model.addAttribute("product", new Product());
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "/product/add-product";
//    }
//    // Process the form for adding a new product
//    @PostMapping("/add")
////    @PreAuthorize("hasAuthority('ADMIN')")
//    public String addProduct(@Valid Product product, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/product/add-product";
//        }
//        productService.addProduct(product);
//        return "redirect:/product";
//    }
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Product product = productService.getProductById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
//        model.addAttribute("product", product);
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "/product/update-product";
//    }
//    @PostMapping("/update/{id}")
//    public String updateProduct(@PathVariable Long id, @Valid Product product,
//                                BindingResult result) {
//        if (result.hasErrors()) {
//            product.setId(Math.toIntExact(id));
//            return "/product/update-product";
//        }
//        productService.updateProduct(product);
//        return "redirect:/product";
//    }
//    // Handle request to delete a product
//    @GetMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable Long id) {
//        productService.deleteProductById(id);
//        return "redirect:/product";
//    }
