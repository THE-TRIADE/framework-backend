package imd.ufrn.instancepetroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.instancepetroutine.model.Category;
import imd.ufrn.instancepetroutine.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category findCategoryById(Long categoryId) {
        return this.categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> new EntityNotFoundException(categoryId, Category.class)
            );
    }
}
