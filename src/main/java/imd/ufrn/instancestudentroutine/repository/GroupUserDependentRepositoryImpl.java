package imd.ufrn.instancestudentroutine.repository;

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

import imd.ufrn.framework.repository.GroupUserDependentRepository;
import imd.ufrn.instancestudentroutine.model.GroupUserDependentStandard;
import imd.ufrn.instancestudentroutine.repository.mappers.GroupUserDependentMapper;

@Repository
public class GroupUserDependentRepositoryImpl implements GroupUserDependentRepository<GroupUserDependentStandard> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<GroupUserDependentStandard> findAll() { 
    String sql = "SELECT * FROM group_user_dependent";
    return jdbcTemplate.query(sql, new GroupUserDependentMapper());
  }

  @Override
  public Optional<GroupUserDependentStandard> findById(Long id) {
    String sql = "SELECT * FROM group_user_dependent WHERE id = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new GroupUserDependentMapper(), id));
    }
    catch(EmptyResultDataAccessException ex) {
        return Optional.empty();
    }
  }

  @Override
  public Optional<GroupUserDependentStandard> findByDependentId(Long dependentId) {
    String sql = "SELECT * FROM group_user_dependent INNER JOIN dependent_group ON group_user_dependent.id = dependent_group.groupId WHERE dependent_group.dependentId = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new GroupUserDependentMapper(), dependentId));
    }
    catch(EmptyResultDataAccessException ex) {
        return Optional.empty();
    }
  }

  @Override
  public GroupUserDependentStandard save(GroupUserDependentStandard group) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO group_user_dependent (`name`) VALUES (?)";
    
    
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, group.getName());
      return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue()).get();
  }
  
  @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM group_user_dependent WHERE id = ?";
    jdbcTemplate.update(sql, new Object[] { id });
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE FROM group_user_dependent";
    jdbcTemplate.update(sql);
  }

}
