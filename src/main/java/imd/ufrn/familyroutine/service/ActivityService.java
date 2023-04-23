package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.ActivityState;
import imd.ufrn.familyroutine.model.api.ActivityMapper;
import imd.ufrn.familyroutine.model.api.request.ActivityRequest;
import imd.ufrn.familyroutine.model.api.request.FinishActivityRequest;
import imd.ufrn.familyroutine.model.api.response.ActivityResponse;
import imd.ufrn.familyroutine.repository.ActivityRepository;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;
import imd.ufrn.familyroutine.service.exception.InvalidStateException;

@Service
public class ActivityService {
	@Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ServiceMediator serviceMediator;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ActivityMapper activityMapper;

    public List<ActivityResponse> findAll() {
        return this.activityRepository
                .findAll()
                .stream()
                .map(this.activityMapper::mapActivityToActivityResponse)
                .toList();
    }

    public ActivityResponse findActivityById(Long activityId) {
        return this.activityMapper.mapActivityToActivityResponse(this.getActivityById(activityId));
    }
    
    public ActivityResponse handleActivityRequest(ActivityRequest activityRequest) {
        if(activityRequest.isRepeat()) {
            return this.activityMapper.mapActivityToActivityResponse(this.serviceMediator.createRecurringActivities(activityRequest));
        }
        else {
            Activity newActivity = this.activityMapper.mapActivityRequestToActivity(activityRequest);
            return this.activityMapper.mapActivityToActivityResponse(this.createActivity(newActivity));
        }
    }

    public ActivityResponse finishActivity(Long activityId, FinishActivityRequest finishActivityRequest) {
        Activity activity = this.getActivityById(activityId);
        this.checkActivityInDoneOrNotDoneStateOrError(activity);
        activity.setState(ActivityState.DONE);
        activity.setFinishedBy(finishActivityRequest.getGuardianId());
        activity.setCommentary(finishActivityRequest.getCommentary());
        return this.activityMapper.mapActivityToActivityResponse(this.updateActivity(activity));
    }

    public void deleteAllActivities() {
        this.activityRepository.deleteAll();
	}

    public void deleteActivityById(Long activityId) {
        this.activityRepository.deleteById(activityId);
    }

    protected void checkActivityInDoneOrNotDoneStateOrError(Activity activity) {
        if (activity.getState() == ActivityState.DONE || activity.getState() == ActivityState.NOT_DONE) {
            throw new InvalidStateException(activity, List.of(ActivityState.CREATED, ActivityState.IN_PROGRESS, ActivityState.LATE));
        }
    }

    protected List<Activity> createMultipleActivities(List<Activity> newActivities) {
        return newActivities.stream().map(this::createActivity).toList();
    }

    protected Activity createActivity(Activity newActivity) {
        this.validationService.validActivityOrError(newActivity);
        return this.activityRepository.save(newActivity);
    }

    private Activity updateActivity(Activity activity) {
        return this.activityRepository.update(activity);
    }

    private Activity getActivityById(Long activityId) {
        return this.activityRepository.findById(activityId).orElseThrow(() -> new EntityNotFoundException(activityId, Activity.class));
    }
}
