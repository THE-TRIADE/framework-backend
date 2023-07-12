package imd.ufrn.instancestudentroutine.model.api;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.UserService;
import imd.ufrn.instancestudentroutine.model.ActivityWithCourse;
import imd.ufrn.instancestudentroutine.model.Course;
import imd.ufrn.instancestudentroutine.model.DependentStudent;
import imd.ufrn.instancestudentroutine.model.api.request.ActivityWithCourseRequest;
import imd.ufrn.instancestudentroutine.model.api.response.ActivityWithCourseResponse;
import imd.ufrn.instancestudentroutine.service.CourseService;

@Component
public class ActivityWithCourseMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private DependentService<DependentStudent> dependentService;
    @Autowired
    private CourseService courseService;

    public ActivityWithCourse mapActivityRequestToActivity(ActivityWithCourseRequest activityRequest) {
        ActivityWithCourse activity = new ActivityWithCourse();
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
        activity.setCourseId(activityRequest.getCourseId());
        return activity;
    }

    public ActivityWithCourseResponse mapActivityToActivityResponse(ActivityWithCourse activity) {
        ActivityWithCourseResponse activityResponse = new ActivityWithCourseResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setName(activity.getName());
        activityResponse.setState(activity.getState());
        activityResponse.setDateStart(activity.getDateStart());
        activityResponse.setDateEnd(activity.getDateEnd());
        activityResponse.setHourStart(activity.getHourStart());
        activityResponse.setHourEnd(activity.getHourEnd());
        activityResponse.setDependentId(activity.getDependentId());
        DependentStudent dependent = this.dependentService.findDependentById(activity.getDependentId());
        activityResponse.setDependentName(dependent.getName());

        activityResponse.setCurrentUserId(activity.getCurrentUser());
        User currentUser = this.userService.findUserById(activity.getCurrentUser());
        activityResponse.setCurrentUserEmail(currentUser.getEmail());
        activityResponse.setCurrentUserName(currentUser.getName());
        
        activityResponse.setCourseId(activity.getCourseId());
        Course course = this.courseService.findCourseById(activity.getCourseId());
        activityResponse.setCourseName(course.getName());
        
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
