package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.ActivityState;
import imd.ufrn.familyroutine.model.api.ActivityRequest;
import imd.ufrn.familyroutine.model.api.FinishActivityRequest;
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

    public List<Activity> findAll() {
        return this.activityRepository.findAll();
    }

    public Activity findActivityById(Long activityId) {
        return this.activityRepository.findById(activityId).orElseThrow(() -> new EntityNotFoundException(activityId, Activity.class));
    }

    public void deleteActivityById(Long activityId) {
        this.activityRepository.deleteById(activityId);
    }
    
    public Activity handleActivityRequest(ActivityRequest activityRequest) {
        if(activityRequest.isRepeat()) {
            return this.serviceMediator.createRecurringActivities(activityRequest);
        }
        else {
            Activity newActivity = mapActivityRequestToActivity(activityRequest);
            return this.createActivity(newActivity);
        }
    }

	public void deleteAllActivities() {
        this.activityRepository.deleteAll();
	}

    public Activity finishActivity(Long activityId, FinishActivityRequest finishActivityRequest) {
        Activity activity = this.findActivityById(activityId);
        this.checkActivityInDoneOrNotDoneStateOrError(activity);
        activity.setState(ActivityState.DONE);
        activity.setFinishedBy(finishActivityRequest.getGuardianId());
        activity.setCommentary(finishActivityRequest.getCommentary());
        return this.updateActivity(activity);
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

    
    protected Activity mapActivityRequestToActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        activity.setName(activityRequest.getName());
        activity.setDateStart(activityRequest.getDateStart());
        activity.setDateEnd(activityRequest.getDateEnd());
        activity.setHourStart(activityRequest.getHourStart());
        activity.setHourEnd(activityRequest.getHourEnd());
        activity.setDependentId(activityRequest.getDependentId());
        activity.setCurrentGuardian(activityRequest.getCurrentGuardian());
        activity.setActor(activityRequest.getActor());
        activity.setCreatedBy(activityRequest.getCreatedBy());
        activity.setState(activityRequest.getState());
        return activity;
    }

    private Activity updateActivity(Activity activity) {
        return this.activityRepository.update(activity);
    }
}
