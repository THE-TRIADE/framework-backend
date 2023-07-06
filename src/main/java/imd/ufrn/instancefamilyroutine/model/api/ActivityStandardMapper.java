package imd.ufrn.instancefamilyroutine.model.api;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.UserService;
import imd.ufrn.instancefamilyroutine.model.ActivityStandard;
import imd.ufrn.instancefamilyroutine.model.DependentStandard;
import imd.ufrn.instancefamilyroutine.model.api.request.ActivityStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.response.ActivityStandardResponse;

@Component
public class ActivityStandardMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private DependentService<DependentStandard> dependentService;

    public ActivityStandard mapActivityRequestToActivity(ActivityStandardRequest activityRequest) {
        ActivityStandard activity = new ActivityStandard();
        activity.setName(activityRequest.getName());
        activity.setDateStart(Date.valueOf(activityRequest.getDateStart()));
        activity.setDateEnd(Date.valueOf(activityRequest.getDateEnd()));
        activity.setHourStart(Time.valueOf(activityRequest.getHourStart()));
        activity.setHourEnd(Time.valueOf(activityRequest.getHourEnd()));
        activity.setDependentId(activityRequest.getDependentId());
        activity.setCurrentUser(activityRequest.getCurrentUser());
        activity.setActor(activityRequest.getActor());
        activity.setCreatedBy(activityRequest.getCreatedBy());
        activity.setState(activityRequest.getState());
        return activity;
    }

    public ActivityStandardResponse mapActivityToActivityResponse(ActivityStandard activity) {
        ActivityStandardResponse activityResponse = new ActivityStandardResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setName(activity.getName());
        activityResponse.setState(activity.getState());
        activityResponse.setDateStart(activity.getDateStart());
        activityResponse.setDateEnd(activity.getDateEnd());
        activityResponse.setHourStart(activity.getHourStart());
        activityResponse.setHourEnd(activity.getHourEnd());
        activityResponse.setDependentId(activity.getDependentId());
        DependentStandard dependent = this.dependentService.findDependentById(activity.getDependentId());
        activityResponse.setDependentName(dependent.getName());

        activityResponse.setCurrentUserId(activity.getCurrentUser());
        User currentUser = this.userService.findUserById(activity.getCurrentUser());
        activityResponse.setCurrentUserEmail(currentUser.getEmail());
        activityResponse.setCurrentUserName(currentUser.getName());
        
        activityResponse.setActorId(activity.getActor());
        if(activity.getActor() == activity.getDependentId()){
            activityResponse.setActorName(dependent.getName());
        }else{
            User user = this.userService.findUserById(activity.getActor());
            activityResponse.setActorName(user.getName());
        }

        activityResponse.setCreatedById(activity.getCreatedBy());
        User createdByUser = this.userService.findUserById(activity.getCreatedBy());
        activityResponse.setCreatedByEmail(createdByUser.getEmail());
        activityResponse.setCreatedByName(createdByUser.getName());
        activityResponse.setRecurringActivityId(activity.getRecurringActivityId());

        if(activity.getCommentary() != null) {
            activityResponse.setCommentary(activity.getCommentary());
        }
        if(activity.getFinishedBy() != null) {
            activityResponse.setFinishedById(activity.getFinishedBy());
            User finishedByUser = this.userService.findUserById(activity.getFinishedBy());
            activityResponse.setFinishedByName(finishedByUser.getName());
        }

        return activityResponse;
    }
}
