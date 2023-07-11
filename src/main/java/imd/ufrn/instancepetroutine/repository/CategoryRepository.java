package imd.ufrn.instancepetroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.instancepetroutine.model.Category;

public interface CategoryRepository {
    List<Category> findAll();

    Optional<Category> findById(Long id);
}
