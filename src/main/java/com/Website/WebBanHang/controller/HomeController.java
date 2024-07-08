package com.Website.WebBanHang.controller;

import com.Website.WebBanHang.model.*;
import com.Website.WebBanHang.Service.BlogService;
import com.Website.WebBanHang.Service.MenuService;
import com.Website.WebBanHang.Service.ProductService;
import com.Website.WebBanHang.Service.SlideService;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import com.Website.WebBanHang.Service.CategoryService;

import com.Website.WebBanHang.model.Category;
import com.Website.WebBanHang.model.Menu;
import com.Website.WebBanHang.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class HomeController {
    private final ProductService productService;
    private final BlogService blogService;
    private final MenuService menuService;
    private final SlideService slideService;

    private final CategoryService categoryService;

    @GetMapping
    public String Home(Model model, Authentication authentication) {
        List<Blog> blogs = blogService.findAllBlogs();
        List<Menu> menus = menuService.findAllMenu();
        List<Slide> slides = slideService.findAllSlide();
        Map<Category, List<Product>> categoryProducts = productService.getTop3ProductsByCategory();

        model.addAttribute("categoryProducts", categoryProducts);
        model.addAttribute("slides", slides);
        model.addAttribute("blogs", blogs);
        model.addAttribute("menus", menus);

        // Check if the user is authenticated and if it's an OAuth2 user
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
            model.addAttribute("userName", attributes.get("name"));
            model.addAttribute("userEmail", attributes.get("email"));
            model.addAttribute("userPicture", attributes.get("picture"));
        }

        return "index";
    }

}
