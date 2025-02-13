package com.Website.WebBanHang.controller;

import org.springframework.ui.Model;
import com.Website.WebBanHang.Service.CategoryService;
import com.Website.WebBanHang.model.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "/categories/add-category";
    }

    @PostMapping("/add")
    public String addCategory(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "/categories/add-category";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "/categories/categories-list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .getBody().orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "/categories/update-category";
    }
    // POST request to update category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @Valid Category
            category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(Math.toIntExact(id));
            return "/categories/update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/categories";
    }
    // GET request for deleting category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .getBody().orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        categoryService.deleteCategoryById(id);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/categories";
    }
}
