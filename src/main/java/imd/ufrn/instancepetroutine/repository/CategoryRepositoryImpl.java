package imd.ufrn.instancepetroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.instancepetroutine.model.Category;
import imd.ufrn.instancepetroutine.repository.mappers.CategoryMapper;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }

    @Override
    public Optional<Category> findById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new CategoryMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    
}
