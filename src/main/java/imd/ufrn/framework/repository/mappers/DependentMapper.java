package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.Dependent;

public class DependentMapper implements RowMapper<Dependent> {

  @Override
  public Dependent mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    Dependent dependent = new Dependent();
    dependent.setId(resultSet.getLong("id"));
    dependent.setName(resultSet.getString("name"));
    dependent.setCpf(resultSet.getString("cpf"));
    dependent.setBirthDate(resultSet.getDate("birthDate"));
    dependent.setFamilyGroupId(resultSet.getLong("familyGroupId"));
    return dependent;
  }
}