package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.repository.mappers.DependentMapper;

@Repository
public class DependentRepositoryImpl implements DependentRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Dependent> findAll() {
    String sql = "SELECT * FROM PERSON INNER JOIN DEPENDENT ON PERSON.id = DEPENDENT.personId";
    return jdbcTemplate.query(sql, new DependentMapper());
  }

  @Override
  public Optional<Dependent> findById(Long id) {
    String sql = "SELECT * FROM PERSON INNER JOIN DEPENDENT ON PERSON.id = DEPENDENT.personId WHERE personId = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new DependentMapper(), id));
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    } 
  }

  @Override
  public Dependent save(Dependent dependent) {
    String sql = "INSERT INTO dependent (personId, groupId) VALUES (?, ?)";
    jdbcTemplate.update(sql, dependent.getId(), dependent.getGroupId());
    return dependent;
  }

}
