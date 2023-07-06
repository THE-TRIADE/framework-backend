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

import imd.ufrn.framework.model.GroupUserDependent;
import imd.ufrn.framework.repository.mappers.GroupUserDependentMapper;

@Repository
public class GroupUserDependentRepositoryImpl implements GroupUserDependentRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<GroupUserDependent> findAll() { 
    String sql = "SELECT * FROM group_user_dependent";
    return jdbcTemplate.query(sql, new GroupUserDependentMapper());
  }

  @Override
  public Optional<GroupUserDependent> findById(Long id) {
    String sql = "SELECT * FROM group_user_dependent WHERE id = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new GroupUserDependentMapper(), id));
    }
    catch(EmptyResultDataAccessException ex) {
        return Optional.empty();
    }
  }

  @Override
  public Optional<GroupUserDependent> findByDependentId(Long dependentId) {
    String sql = "SELECT * FROM group_user_dependent INNER JOIN dependent ON group_user_dependent.id = dependent.groupId WHERE dependent.id = ?";
    try {
        return Optional.of(jdbcTemplate.queryForObject(sql, new GroupUserDependentMapper(), dependentId));
    }
    catch(EmptyResultDataAccessException ex) {
        return Optional.empty();
    }
  }

  @Override
  public GroupUserDependent save(GroupUserDependent group) {
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
