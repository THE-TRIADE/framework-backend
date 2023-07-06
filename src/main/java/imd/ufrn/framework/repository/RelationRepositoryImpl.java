package imd.ufrn.framework.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.framework.model.Relation;
import imd.ufrn.framework.repository.mappers.RelationMapper;

@Repository
public class RelationRepositoryImpl implements RelationRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Relation> findAll() {
    String sql = "SELECT * FROM relation";
    return jdbcTemplate.query(sql, new RelationMapper());
  }

  @Override
  public Optional<Relation> findById(Long id) {
    String sql = "SELECT * FROM relation WHERE id = ?";
    try {
      return Optional.of(jdbcTemplate.queryForObject(sql, new RelationMapper(), id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Relation> findByUserId(Long id) {
    String sql = "SELECT * FROM relation WHERE userId = ?";
    return jdbcTemplate.query(sql, new RelationMapper(), id);
  }

  @Override
  public List<Relation> findByDependentId(Long id) {
    String sql = "SELECT * FROM relation WHERE dependentId = ?";
    return jdbcTemplate.query(sql, new RelationMapper(), id);
  }

  @Override
  public Relation save(Relation relation) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO relation (daysOfWeek, userRole, dependentId, userId) VALUES (?,?,?,?)";

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      if (relation.getDaysOfWeek() == null) {
        ps.setNull(1, Types.VARCHAR);
      }
      else {
        ps.setString(1, relation.getDaysOfWeek().stream().map(Enum::toString).collect(Collectors.joining(",")));
      }
      ps.setString(2, relation.getUserRole().toString());
      ps.setLong(3, relation.getDependentId());
      ps.setLong(4, relation.getUserId());

      return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue()).get();
  }

  @Override
  public Relation update(Relation relation) {
    String sql = "UPDATE relation SET daysOfWeek = ?, userRole = ? WHERE id = ?";

    jdbcTemplate.update(sql,
            relation.getDaysOfWeek().stream().map(Enum::toString).collect(Collectors.joining(",")),
            relation.getUserRole().toString(),
            relation.getId());
    return relation;
  }

  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM relation WHERE id = ?";
    jdbcTemplate.update(sql, new Object[] { id });
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE FROM relation";
    jdbcTemplate.update(sql);
  }

}
