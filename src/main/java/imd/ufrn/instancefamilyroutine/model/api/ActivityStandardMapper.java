package imd.ufrn.instancefamilyroutine.model.api;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.Guardian;
import imd.ufrn.framework.model.Person;
import imd.ufrn.framework.service.GuardianService;
import imd.ufrn.framework.service.PersonService;
import imd.ufrn.instancefamilyroutine.model.ActivityStandard;
import imd.ufrn.instancefamilyroutine.model.api.request.ActivityStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.response.ActivityStandardResponse;

@Component
public class ActivityStandardMapper {

    @Autowired
    private GuardianService guardianService;
    @Autowired
    private PersonService personService;

    public ActivityStandard mapActivityRequestToActivity(ActivityStandardRequest activityRequest) {
        ActivityStandard activity = new ActivityStandard();
        activity.setName(activityRequest.getName());
        activity.setDateStart(Date.valueOf(activityRequest.getDateStart()));
        activity.setDateEnd(Date.valueOf(activityRequest.getDateEnd()));
        activity.setHourStart(Time.valueOf(activityRequest.getHourStart()));
        activity.setHourEnd(Time.valueOf(activityRequest.getHourEnd()));
        activity.setDependentId(activityRequest.getDependentId());
        activity.setCurrentGuardian(activityRequest.getCurrentGuardian());
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
        Person dependent = this.personService.findPersonById(activity.getDependentId());
        activityResponse.setDependentName(dependent.getName());

        activityResponse.setCurrentGuardianId(activity.getCurrentGuardian());
        Guardian currentGuardian = this.guardianService.findGuardianById(activity.getCurrentGuardian());
        activityResponse.setCurrentGuardianEmail(currentGuardian.getEmail());
        activityResponse.setCurrentGuardianName(currentGuardian.getName());
        
        activityResponse.setActorId(activity.getActor());
        Person actor = this.personService.findPersonById(activity.getActor());
        activityResponse.setActorName(actor.getName());

        activityResponse.setCreatedById(activity.getCreatedBy());
        Guardian createdByGuardian = this.guardianService.findGuardianById(activity.getCreatedBy());
        activityResponse.setCreatedByEmail(createdByGuardian.getEmail());
        activityResponse.setCreatedByName(createdByGuardian.getName());
        activityResponse.setRecurringActivityId(activity.getRecurringActivityId());

        if(activity.getCommentary() != null) {
            activityResponse.setCommentary(activity.getCommentary());
        }
        if(activity.getFinishedBy() != null) {
            activityResponse.setFinishedById(activity.getFinishedBy());
        }

        return activityResponse;
    }
}
