package imd.ufrn.framework.repository;

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

import imd.ufrn.framework.model.Spent;
import imd.ufrn.framework.repository.mappers.SpentMapper;

@Repository
public class SpentRepositoryImpl implements SpentRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Spent> findAll() {
    String sql = "SELECT * FROM spent";
    return jdbcTemplate.query(sql, new SpentMapper());
  }

  @Override
  public Optional<Spent> findById(Long id) {
    String sql = "SELECT * FROM spent WHERE id = ?";
    try {
      return Optional.of(jdbcTemplate.queryForObject(sql, new SpentMapper(), id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Spent> findByUserId(Long id) {
    String sql = "SELECT * FROM spent WHERE userId = ?";
    return jdbcTemplate.query(sql, new SpentMapper(), id);
  }

  @Override
  public List<Spent> findByDependentId(Long id) {
    String sql = "SELECT * FROM spent WHERE dependentId = ?";
    return jdbcTemplate.query(sql, new SpentMapper(), id);
  }

  @Override
  public Spent save(Spent spent) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO spent (name, paidOn, `value`, activityId, dependentId, userId) VALUES (?,?,?,?,?,?)";

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, spent.getName());
      ps.setDate(2, spent.getPaidOn());
      ps.setInt(3, spent.getValue());
      if (spent.getActivityId() != null) {
        ps.setLong(4, spent.getActivityId());
      } else {
        ps.setNull(4, 0);
      }
      ps.setLong(5, spent.getDependentId());
      ps.setLong(6, spent.getUserId());

      return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue()).get();
  }

  @Override
  public Spent update(Spent spent) {
    String sql = "UPDATE spent SET name = ?, value = ? WHERE id = ?";

    jdbcTemplate.update(sql,
        spent.getName(),
        spent.getValue(),
        spent.getId());
    return spent;
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM spent WHERE id = ?";
    jdbcTemplate.update(sql, new Object[] { id });
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE FROM spent";
    jdbcTemplate.update(sql);
  }

}
