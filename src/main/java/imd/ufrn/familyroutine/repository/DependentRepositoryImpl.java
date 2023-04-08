package imd.ufrn.familyroutine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.Dependent;

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
    String sql = "SELECT * FROM dependent WHERE id = " + id;
    return jdbcTemplate.queryForObject(sql, new Object[] { id }, new DependentMapper());
  }

  @Override
  public Dependent save(Dependent dependent) {
    String sql = "INSERT INTO dependent (id, `name`, cpf, birthDate) VALUES (?,?,?,?)";
    jdbcTemplate.update(sql, dependent.getId(), dependent.getName(), dependent.getCpf(),
        dependent.getBirthDate());
    return null;
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE * FROM dependent WHERE id = " + id;
    jdbcTemplate.update(sql, id > 0);
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE * FROM dependent";
    jdbcTemplate.update(sql, true);
  }

}
