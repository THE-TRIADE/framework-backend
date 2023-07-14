package imd.ufrn.instancepetroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql = "INSERT INTO `dependent` (id, `name`, race, birthDate) VALUES (?,?,?,?)";
        dependent.setId(getNextId());
        jdbcTemplate.update(sql, dependent.getId(), dependent.getName(), dependent.getRace(), dependent.getBirthDate());
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
