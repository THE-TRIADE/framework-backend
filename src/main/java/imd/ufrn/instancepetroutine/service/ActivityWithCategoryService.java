package imd.ufrn.instancepetroutine.service;

import java.sql.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.ActivityState;
import imd.ufrn.framework.service.ActivityService;
import imd.ufrn.instancepetroutine.model.ActivityWithCategory;
import imd.ufrn.instancepetroutine.model.api.ActivityWithCategoryMapper;
import imd.ufrn.instancepetroutine.model.api.request.ActivityWithCategoryRequest;
import imd.ufrn.instancepetroutine.model.api.request.FinishActivityWithCategoryRequest;
import imd.ufrn.instancepetroutine.model.api.response.ActivityWithCategoryResponse;

@Service
public class ActivityWithCategoryService extends ActivityService<ActivityWithCategory, ActivityWithCategoryRequest, FinishActivityWithCategoryRequest, ActivityWithCategoryResponse> {

    @Autowired
    private ActivityWithCategoryMapper activityWithCategoryMapper;

    @Override
    protected ActivityWithCategoryResponse mapToResponse(ActivityWithCategory activity) {
        return this.activityWithCategoryMapper.mapActivityToActivityResponse(activity);
    }

    @Override
    protected ActivityWithCategory mapToEntity(ActivityWithCategoryRequest activityRequest) {
        return this.activityWithCategoryMapper.mapActivityRequestToActivity(activityRequest);
    }

    @Override
    protected ActivityWithCategory mapToEntityDiffDates(Date startDate, Date endDate,
            ActivityWithCategoryRequest activityRequest) {
        ActivityWithCategory activity = this.activityWithCategoryMapper.mapActivityRequestToActivity(activityRequest);
        activity.setDateStart(startDate);
        activity.setDateEnd(endDate);
        return activity;
    }

    @Override
    protected ActivityWithCategory mapToEntity(FinishActivityWithCategoryRequest finishRequest, ActivityWithCategory activity) {
        Function<Boolean,ActivityState> defineFinishState = done -> done == true ? ActivityState.DONE : ActivityState.NOT_DONE;
        activity.setState(defineFinishState.apply(finishRequest.getDone()));
        activity.setFinishedBy(finishRequest.getUserId());
        activity.setCommentary(finishRequest.getCommentary());
        return activity;
    }

    public List<ActivityWithCategoryResponse> findActivitiesByCategoryId(Long categoryId) {
        return this
            .findAll()
            .stream()
            .filter(activity -> activity.getCategoryId().equals(categoryId))
            .toList();
    }

    public List<ActivityWithCategoryResponse> findActivitiesByDependentIdAndCategoryId(Long dependentId, Long categoryId) {
        return this.findAll()
                .stream()
                .filter(activity -> activity.getDependentId().equals(dependentId) && activity.getCategoryId().equals(categoryId))
                .toList();
    }

}
