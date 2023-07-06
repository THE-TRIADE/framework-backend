package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.*;

public class RelationMapper implements RowMapper<Relation> {

  @Override
  public Relation mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    Relation relation = new Relation();
    relation.setId(resultSet.getLong("id"));

    String daysOfWeek = resultSet.getString("daysOfWeek");

    if (daysOfWeek != null) {
      relation.setDaysOfWeek(
          List.of(daysOfWeek.split(","))
              .stream()
              .map(string -> DayOfWeek.valueOf(string))
              .collect(Collectors.toList()));
    }

    relation.setUserRole(GuardianRole.valueOf(resultSet.getString("userRole")));
    relation.setDependentId(resultSet.getLong("dependentId"));
    relation.setUserId(resultSet.getLong("userId"));

    return relation;
  }

}
