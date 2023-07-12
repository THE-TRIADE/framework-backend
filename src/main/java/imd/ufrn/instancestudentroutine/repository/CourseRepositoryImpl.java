package imd.ufrn.instancestudentroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.instancestudentroutine.model.Course;
import imd.ufrn.instancestudentroutine.repository.mappers.CourseMapper;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public Optional<Course> findById(Long id) {
        String sql = "SELECT * FROM course WHERE id = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new CourseMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    
}
