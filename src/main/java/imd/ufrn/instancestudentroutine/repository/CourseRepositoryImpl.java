package imd.ufrn.instancestudentroutine.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import imd.ufrn.instancestudentroutine.model.UserInGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @Override
    public Course save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO course (`name`) VALUES (?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, course.getName());
            return ps;
        }, keyHolder);

        return this.findById(keyHolder.getKey().longValue()).get();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM course WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM course";
        jdbcTemplate.update(sql);
    }
}
