package imd.ufrn.instancestudentroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancestudentroutine.model.DependentStudent;

public class DependentStudentMapper implements RowMapper<DependentStudent> {

  @Override
  public DependentStudent mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    DependentStudent dependentStudent = new DependentStudent();
    dependentStudent.setId(resultSet.getLong("id"));
    dependentStudent.setName(resultSet.getString("name"));
    dependentStudent.setCpf(resultSet.getString("cpf"));
    dependentStudent.setRegistrationNumber(resultSet.getString("registrationNumber"));

    
    return dependentStudent;
  }
}