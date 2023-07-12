package imd.ufrn.instancestudentroutine.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.ActivityService;
import imd.ufrn.instancestudentroutine.model.ActivityWithCourse;
import imd.ufrn.instancestudentroutine.model.api.ActivityWithCourseMapper;
import imd.ufrn.instancestudentroutine.model.api.request.ActivityWithCourseRequest;
import imd.ufrn.instancestudentroutine.model.api.request.FinishActivityWithCourseRequest;
import imd.ufrn.instancestudentroutine.model.api.response.ActivityWithCourseResponse;

@Service
public class ActivityWithCourseService extends ActivityService<ActivityWithCourse, ActivityWithCourseRequest, FinishActivityWithCourseRequest, ActivityWithCourseResponse> {

    @Autowired
    private ActivityWithCourseMapper activityWithCourseMapper;

    @Override
    protected ActivityWithCourseResponse mapToResponse(ActivityWithCourse activity) {
        return this.activityWithCourseMapper.mapActivityToActivityResponse(activity);
    }

    @Override
    protected ActivityWithCourse mapToEntity(ActivityWithCourseRequest activityRequest) {
        return this.activityWithCourseMapper.mapActivityRequestToActivity(activityRequest);
    }

    @Override
    protected ActivityWithCourse mapToEntityDiffDates(Date startDate, Date endDate,
            ActivityWithCourseRequest activityRequest) {
        ActivityWithCourse activity = this.activityWithCourseMapper.mapActivityRequestToActivity(activityRequest);
        activity.setDateStart(startDate);
        activity.setDateEnd(endDate);
        return activity;
    }

    public List<ActivityWithCourseResponse> findActivitiesByCourseId(Long courseId) {
        return this
            .findAll()
            .stream()
            .filter(activity -> activity.getCourseId().equals(courseId))
            .toList();
    }

    public List<ActivityWithCourseResponse> findActivitiesByDependentIdAndCourseId(Long dependentId, Long courseId) {
        return this.findAll()
                .stream()
                .filter(activity -> activity.getDependentId().equals(dependentId) && activity.getCourseId().equals(courseId))
                .toList();
    }

}
