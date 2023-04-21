package imd.ufrn.familyroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.ActivityState;

public class ActivityMapper implements RowMapper<Activity>{
    @Override
    public Activity mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    Activity activity = new Activity();
    activity.setId(resultSet.getLong("id"));
    activity.setName(resultSet.getString("name"));
    activity.setDescription(resultSet.getString("description"));
    activity.setDateStart(resultSet.getDate("dateStart"));
    activity.setDateEnd(resultSet.getDate("dateEnd"));
    activity.setHourStart(resultSet.getTime("hourStart"));
    activity.setHourEnd(resultSet.getTime("hourEnd"));
    activity.setState(ActivityState.valueOf(resultSet.getString("state")));
    activity.setCommentary(resultSet.getString("commentary"));
    activity.setDependentId(resultSet.getLong("dependentId"));
    activity.setCurrentGuardian(resultSet.getLong("currentGuardianId"));
    activity.setCreatedBy(resultSet.getLong("createdBy"));
    activity.setActor(resultSet.getLong("actorId"));
    return activity;
  }
    
}