package imd.ufrn.instancestudentroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.ActivityState;
import imd.ufrn.instancestudentroutine.model.ActivityWithCourse;

public class ActivityWithCourseMapper implements RowMapper<ActivityWithCourse>{
    @Override
    public ActivityWithCourse mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
    ActivityWithCourse activity = new ActivityWithCourse();
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
    activity.setCurrentUser(resultSet.getLong("currentUserId"));
    activity.setActor(resultSet.getLong("actorId"));
    activity.setCreatedBy(resultSet.getLong("createdBy"));
    Long finishedBy = resultSet.getLong("finishedBy");
    if(finishedBy != 0){
        activity.setFinishedBy(finishedBy);
    } 
    activity.setRecurringActivityId(resultSet.getLong("recurringActivityId"));
    activity.setCourseId(resultSet.getLong("courseId"));
    return activity;
  }
    
}