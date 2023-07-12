package imd.ufrn.instancepetroutine.repository;

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
import imd.ufrn.instancepetroutine.model.DependentPet;
import imd.ufrn.instancepetroutine.repository.mappers.DependentPetMapper;

@Repository
public class DependentPetRepositoryImpl implements DependentRepository<DependentPet> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<DependentPet> findAll() {
    String sql = "SELECT * FROM DEPENDENT";
    return jdbcTemplate.query(sql, new DependentPetMapper());
  }

  @Override
  public Optional<DependentPet> findById(Long id) {
    String sql = "SELECT * FROM DEPENDENT WHERE id = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new DependentPetMapper(), id));
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    } 
  }

  @Override
  public DependentPet save(DependentPet dependent) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO dependent (`name`, race, birthDate) VALUES (?, ?, ?)";

    jdbcTemplate.update(connection -> { 
    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, dependent.getName());
      ps.setString(2, dependent.getRace());
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
