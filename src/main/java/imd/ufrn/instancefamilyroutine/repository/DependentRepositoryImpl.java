package imd.ufrn.instancefamilyroutine.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.framework.repository.DependentRepository;
import imd.ufrn.instancefamilyroutine.model.DependentStandard;
import imd.ufrn.instancefamilyroutine.repository.mappers.DependentStandardMapper;

@Repository
public class DependentRepositoryImpl implements DependentRepository<DependentStandard> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<DependentStandard> findAll() {
    String sql = "SELECT * FROM DEPENDENT";
    return jdbcTemplate.query(sql, new DependentStandardMapper());
  }

  @Override
  public Optional<DependentStandard> findById(Long id) {
    String sql = "SELECT * FROM DEPENDENT WHERE id = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new DependentStandardMapper(), id));
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    } 
  }

  @Override
  public DependentStandard save(DependentStandard dependent) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO dependent (`name`, cpf, birthDate) VALUES (?, ?, ?)";

    jdbcTemplate.update(connection -> { 
    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, dependent.getName());
      ps.setString(2, dependent.getCpf());
      ps.setDate(3, dependent.getBirthDate());
      return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue()).get();
  }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM `dependent` WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM `dependent`";
        jdbcTemplate.update(sql);
    }
}