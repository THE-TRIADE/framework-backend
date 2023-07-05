package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.*;

public class GuardMapper implements RowMapper<Guard> {

  @Override
  public Guard mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    Guard guard = new Guard();
    guard.setId(resultSet.getLong("id"));

    String daysOfWeek = resultSet.getString("daysOfWeek");

    if (daysOfWeek != null) {
      guard.setDaysOfWeek(
          List.of(daysOfWeek.split(","))
              .stream()
              .map(string -> DayOfWeek.valueOf(string))
              .collect(Collectors.toList()));
    }

    guard.setGuardianRole(GuardianRole.valueOf(resultSet.getString("guardianRole")));
    guard.setDependentId(resultSet.getLong("dependentId"));
    guard.setGuardianId(resultSet.getLong("guardianId"));

    return guard;
  }

}
