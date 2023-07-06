package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.*;

public class SpentMapper implements RowMapper<Spent> {

  @Override
  public Spent mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    Spent spent = new Spent();
    spent.setId(resultSet.getLong("id"));
    spent.setName(resultSet.getString("name"));
    spent.setPaidOn(resultSet.getDate("paidOn"));
    spent.setValue(resultSet.getInt("value"));
    spent.setDependentId(resultSet.getLong("dependentId"));
    spent.setUserId(resultSet.getLong("userId"));
    if (resultSet.getObject("activityId") != null) {
      spent.setActivityId(resultSet.getLong("activityId"));
    }

    return spent;
  }

}
