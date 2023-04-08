package imd.ufrn.familyroutine.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.FamilyGroup;
import imd.ufrn.familyroutine.repository.mappers.FamilyGroupMapper;

@Repository
public class FamilyGroupRepositoryImpl implements FamilyGroupRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<FamilyGroup> findAll() { 
    String sql = "SELECT * FROM familyGroup";
    return jdbcTemplate.query(sql, new FamilyGroupMapper());
  }

  @Override
  public FamilyGroup findById(Long id) {
    String sql = "SELECT * FROM familyGroup WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[] { id }, new FamilyGroupMapper());
  }

  @Override
  public FamilyGroup save(FamilyGroup familyGroup) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO familyGroup (`name`) VALUES (?)";
    
    
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, familyGroup.getName());
      return ps;
    }, keyHolder);

    return this.findById(keyHolder.getKey().longValue());
  }
  
    @Override
  public void deleteById(Long id) {
    String sql = "DELETE FROM familyGroup WHERE id = ?";
    jdbcTemplate.update(sql, new Object[] { id });
  }

  @Override
  public void deleteAll() {
    String sql = "DELETE FROM familyGroup";
    jdbcTemplate.update(sql);
  }

}
