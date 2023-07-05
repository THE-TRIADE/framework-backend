package imd.ufrn.instancefamilyroutine.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.ActivityService;
import imd.ufrn.instancefamilyroutine.model.ActivityStandard;
import imd.ufrn.instancefamilyroutine.model.api.ActivityStandardMapper;
import imd.ufrn.instancefamilyroutine.model.api.request.ActivityStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.request.FinishActivityStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.response.ActivityStandardResponse;

@Service
public class ActivityStandardService extends ActivityService<ActivityStandard, ActivityStandardRequest, FinishActivityStandardRequest, ActivityStandardResponse> {

    @Autowired
    private ActivityStandardMapper activityStandardMapper;

    @Override
    protected ActivityStandardResponse mapToResponse(ActivityStandard activity) {
        return this.activityStandardMapper.mapActivityToActivityResponse(activity);
    }

    @Override
    protected ActivityStandard mapToEntity(ActivityStandardRequest activityRequest) {
        return this.activityStandardMapper.mapActivityRequestToActivity(activityRequest);
    }

    @Override
    protected ActivityStandard mapToEntityDiffDates(Date startDate, Date endDate,
            ActivityStandardRequest activityRequest) {
        ActivityStandard activity = this.activityStandardMapper.mapActivityRequestToActivity(activityRequest);
        activity.setDateStart(startDate);
        activity.setDateEnd(endDate);
        return activity;
    }
	
}
