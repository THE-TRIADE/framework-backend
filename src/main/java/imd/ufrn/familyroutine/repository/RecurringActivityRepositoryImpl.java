package imd.ufrn.familyroutine.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.RecurringActivity;
import imd.ufrn.familyroutine.repository.mappers.RecurringActivityMapper;

@Repository
public class RecurringActivityRepositoryImpl implements RecurringActivityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<RecurringActivity> findById(Long id) {
        String sql = "SELECT * FROM recurring_activity WHERE id = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new RecurringActivityMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    @Override
    public RecurringActivity save(RecurringActivity activity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO recurring_activity () VALUES ()";

        jdbcTemplate.update(connection -> { 
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);
    
        return this.findById(keyHolder.getKey().longValue()).get();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM recurring_activity WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM recurring_activity";
        jdbcTemplate.update(sql);
    }
}
