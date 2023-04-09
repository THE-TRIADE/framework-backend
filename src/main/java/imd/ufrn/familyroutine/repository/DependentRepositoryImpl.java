package imd.ufrn.familyroutine.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.Dependent;
import imd.ufrn.familyroutine.repository.mappers.DependentMapper;

@Repository
public class DependentRepositoryImpl implements DependentRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Dependent> findAll() {
    String sql = "SELECT * FROM dependent";
    return jdbcTemplate.query(sql, new DependentMapper());
  }

  @Override
  public Dependent findById(Long id) {
    String sql = "SELECT * FROM dependent WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new DependentMapper(), id);
  }

  @Override
  public Dependent save(Dependent dependent) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO dependent (`name`, cpf, birthDate) VALUES (?,?,?)";

    jdbcTemplate.update(connection -> { 
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dependent.getName());
        ps.setString(2, dependent.getCpf());
        ps.setDate(3, (Date) dependent.getBirthDate());
        return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue());
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM dependent WHERE id = ?";
    jdbcTemplate.update(sql, new Object[] { id });
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE FROM dependent";
    jdbcTemplate.update(sql);
  }

}
