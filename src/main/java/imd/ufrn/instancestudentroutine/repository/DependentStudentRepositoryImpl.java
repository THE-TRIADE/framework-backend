package imd.ufrn.instancestudentroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.framework.repository.DependentRepository;
import imd.ufrn.instancestudentroutine.model.DependentStudent;
import imd.ufrn.instancestudentroutine.repository.mappers.DependentStudentMapper;

@Repository
public class DependentStudentRepositoryImpl implements DependentRepository<DependentStudent> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<DependentStudent> findAll() {
    String sql = "SELECT * FROM DEPENDENT";
    return jdbcTemplate.query(sql, new DependentStudentMapper());
  }

  @Override
  public Optional<DependentStudent> findById(Long id) {
    String sql = "SELECT * FROM DEPENDENT WHERE id = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new DependentStudentMapper(), id));
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    } 
  }

    @Override
    public DependentStudent save(DependentStudent dependent) {
        String sql = "INSERT INTO `dependent` (id, `name`, birthDate) VALUES (?,?,?)";
        dependent.setId(getNextId());
        jdbcTemplate.update(sql, dependent.getId(), dependent.getName(), dependent.getBirthDate()); // TODO
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
