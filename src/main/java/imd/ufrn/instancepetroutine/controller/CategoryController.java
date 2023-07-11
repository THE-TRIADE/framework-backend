package imd.ufrn.instancepetroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.instancepetroutine.model.Category;
import imd.ufrn.instancepetroutine.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired 
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return this.categoryService.findAllCategories();
    }

    @GetMapping("{categoryId}")
    public Category findCategoryById(@PathVariable Long categoryId) {
        return this.categoryService.findCategoryById(categoryId);
    }
}
