package imd.ufrn.instancefamilyroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.ActivityState;
import imd.ufrn.instancefamilyroutine.model.ActivityStandard;

public class ActivityStandardMapper implements RowMapper<ActivityStandard>{
    @Override
    public ActivityStandard mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    ActivityStandard activity = new ActivityStandard();
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
    activity.setActor(resultSet.getLong("actorId"));
    activity.setCreatedBy(resultSet.getLong("createdBy"));
    activity.setFinishedBy(resultSet.getLong("finishedBy"));
    activity.setRecurringActivityId(resultSet.getLong("recurringActivityId"));
    return activity;
  }
    
}