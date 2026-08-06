package com.flowpay.backend.controller;

import com.flowpay.backend.dto.CategoryResponse;
import com.flowpay.backend.dto.CreateCategoryRequest;
import com.flowpay.backend.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private static final Logger logger =
            LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse createCategory(
            @Valid @RequestBody CreateCategoryRequest request) {

        logger.info("Creating category: {}", request.getName());

        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {

        logger.info("Fetching all categories.");

        return categoryService.getAllCategories();
    }
}