package imd.ufrn.familyroutine.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.Guard;
import imd.ufrn.familyroutine.repository.mappers.GuardMapper;

@Repository
public class GuardRepositoryImpl implements GuardRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Guard> findAll() {
    String sql = "SELECT * FROM guard";
    return jdbcTemplate.query(sql, new GuardMapper());
  }

  @Override
  public Optional<Guard> findById(Long id) {
    String sql = "SELECT * FROM guard WHERE id = ?";
    try {
      return Optional.of(jdbcTemplate.queryForObject(sql, new GuardMapper(), id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Guard> findByGuardianId(Long id) {
    String sql = "SELECT * FROM guard WHERE guardianId = ?";
    return jdbcTemplate.query(sql, new GuardMapper(), id);
  }

  @Override
  public List<Guard> findByDependentId(Long id) {
    String sql = "SELECT * FROM guard WHERE dependentId = ?";
    return jdbcTemplate.query(sql, new GuardMapper(), id);
  }

  @Override
  public Guard save(Guard guard) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO guard (daysOfTheWeek, guardianRole, dependentId, guardianId) VALUES (?,?,?,?)";

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, guard.getDaysOfTheWeek().stream().map(Enum::toString).collect(Collectors.joining(",")));
      ps.setString(2, guard.getGuardianRole().toString());
      ps.setLong(3, guard.getDependentId());
      ps.setLong(4, guard.getGuardianId());

      return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue()).get();
  }

  @Override
  public Guard update(Guard guard) {
    String sql = "UPDATE guard SET daysOfTheWeek = ?, guardianRole = ? WHERE id = ?";

    jdbcTemplate.update(sql,
        guard.getDaysOfTheWeek().stream().map(Enum::toString).collect(Collectors.joining(",")),
        guard.getGuardianRole().toString(),
        guard.getId());
    return guard;
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM guard WHERE id = ?";
    jdbcTemplate.update(sql, new Object[] { id });
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE FROM guard";
    jdbcTemplate.update(sql);
  }

}
