package imd.ufrn.instancefamilyroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancefamilyroutine.model.DependentStandard;

public class DependentStandardMapper implements RowMapper<DependentStandard> {

  @Override
  public DependentStandard mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    DependentStandard dependentstandard = new DependentStandard();
    dependentstandard.setId(resultSet.getLong("id"));
    dependentstandard.setName(resultSet.getString("name"));
    dependentstandard.setGroupId(resultSet.getLong("groupId"));
    dependentstandard.setBirthDate(resultSet.getDate("birthDate"));
    dependentstandard.setCpf(resultSet.getString("cpf"));
    
    return dependentstandard;
  }
}