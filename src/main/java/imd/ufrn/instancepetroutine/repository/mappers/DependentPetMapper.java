package imd.ufrn.instancepetroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancepetroutine.model.DependentPet;

public class DependentPetMapper implements RowMapper<DependentPet> {

  @Override
  public DependentPet mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    DependentPet dependentPet = new DependentPet();
    dependentPet.setId(resultSet.getLong("id"));
    dependentPet.setName(resultSet.getString("name"));
    dependentPet.setRace(resultSet.getString("race"));
    dependentPet.setBirthDate(resultSet.getDate("birthDate"));
    
    return dependentPet;
  }
}