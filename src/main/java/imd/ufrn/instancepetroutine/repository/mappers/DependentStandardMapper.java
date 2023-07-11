package imd.ufrn.instancepetroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancepetroutine.model.DependentStandard;

public class DependentStandardMapper implements RowMapper<DependentStandard> {

  @Override
  public DependentStandard mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    DependentStandard dependentstandard = new DependentStandard();
    dependentstandard.setId(resultSet.getLong("id"));
    dependentstandard.setName(resultSet.getString("name"));
    dependentstandard.setRace(resultSet.getString("race"));
    dependentstandard.setBirthDate(resultSet.getDate("birthDate"));
    
    return dependentstandard;
  }
}