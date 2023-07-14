package imd.ufrn.instancefamilyroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql = "INSERT INTO `dependent` (id, `name`, cpf, birthDate) VALUES (?,?,?,?)";
        dependent.setId(getNextId());
        jdbcTemplate.update(sql, dependent.getId(), dependent.getName(), dependent.getCpf(), dependent.getBirthDate());
        return dependent;
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

    private Long getNextId() {
        Long maxUserId = jdbcTemplate
            .query("SELECT id FROM `user`", (rs, rn) ->  rs.getBigDecimal("id").longValue())
            .stream()
            .reduce(Long::max)
            .get();

        Long maxDependentId = jdbcTemplate
            .query("SELECT id FROM `dependent`", (rs, rn) ->  rs.getBigDecimal("id").longValue())
            .stream()
            .reduce(Long::max)
            .get();

        if (maxUserId == null && maxDependentId == null) {
            return 1L;
        } else if (maxUserId == null) {
            return maxDependentId + 1;
        } else if (maxDependentId == null) {
            return maxUserId + 1;
        } else {
            return Math.max(maxUserId, maxDependentId) + 1;
        }
    }
}
