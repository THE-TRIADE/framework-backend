package imd.ufrn.framework.service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.framework.model.ActivityAbstract;
import imd.ufrn.framework.model.ActivityState;
import imd.ufrn.framework.model.RecurringActivity;
import imd.ufrn.framework.model.api.request.ActivityRequest;
import imd.ufrn.framework.model.api.request.FinishActivityRequest;
import imd.ufrn.framework.model.api.response.ActivityResponse;
import imd.ufrn.framework.repository.ActivityRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.framework.service.exception.InvalidStateException;
import imd.ufrn.framework.service.exception.RecurringActivityException;
import imd.ufrn.framework.service.exception.RecurringActivityType;
/**
 * @param A should be an ActivityAbstract subtype class.
 * @param B should be an ActivityRequest subtype class.
 * @param C should be an FinishActivityRequest subtype class.
 * @param D should be an ActivityResponse subtype class.
 */
public abstract class ActivityService<A extends ActivityAbstract, B extends ActivityRequest, C extends FinishActivityRequest, D extends ActivityResponse> {
	@Autowired
    private ActivityRepository<A> activityRepository;
    @Autowired
    private RecurringActivityService recurringActivityService;
    @Autowired
    private ValidationService validationService;

    protected abstract D mapToResponse(A activity);
    protected abstract A mapToEntity(B activityRequest);
    protected abstract A mapToEntityDiffDates(Date startDate, Date endDate, B activityRequest);

    public List<D> findAll() {
        return this.activityRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<D> findByDependentId(Long dependentId) {
        return this.activityRepository
                    .findAll()
                    .stream()
                    .filter(activity -> activity.getDependentId().equals(dependentId))
                    .map(this::mapToResponse)
                    .toList();
    }

    public D findActivityById(Long activityId) {
        return this.mapToResponse(this.getActivityById(activityId));
    }
    
    public D handleActivityRequest(B activityRequest) {
        if(activityRequest.isRepeat()) {
            return this.mapToResponse(this.createRecurringActivities(activityRequest));
        }
        else {
            A newActivity = this.mapToEntity(activityRequest);
            return this.mapToResponse(this.createActivity(newActivity));
        }
    }

    public D finishActivity(Long activityId, C finishActivityRequest) {
        A activity = this.getActivityById(activityId);
        this.checkActivityIsNotInStateDoneOrNotDoneOrError(activity);
        Function<Boolean,ActivityState> defineFinishState = done -> done == true ? ActivityState.DONE : ActivityState.NOT_DONE;
        activity.setState(defineFinishState.apply(finishActivityRequest.getDone()));
        activity.setFinishedBy(finishActivityRequest.getUserId());
        activity.setCommentary(finishActivityRequest.getCommentary());
        return this.mapToResponse(this.updateActivity(activity));
    }

    public void deleteAllActivities() {
        this.activityRepository.deleteAll();
	}

    public void deleteActivityById(Long activityId) {
        this.activityRepository.deleteById(activityId);
    }

    protected void checkActivityIsNotInStateDoneOrNotDoneOrError(A activity) {
        if (activity.getState() == ActivityState.DONE || activity.getState() == ActivityState.NOT_DONE) {
            throw new InvalidStateException(activity, List.of(ActivityState.CREATED, ActivityState.IN_PROGRESS, ActivityState.LATE));
        }
    }

    protected List<A> createMultipleActivities(List<A> newActivities) {
        return newActivities.stream().map(this::createActivity).toList();
    }

    protected A createActivity(A newActivity) {
        this.validationService.validActivityOrError(newActivity);
        return this.activityRepository.save(newActivity);
    }

    private A updateActivity(A activity) {
        return this.activityRepository.update(activity);
    }

    private A getActivityById(Long activityId) {
        return this.activityRepository.findById(activityId).orElseThrow(() -> new EntityNotFoundException(activityId, ActivityAbstract.class));
    }

    @Transactional
	private A createRecurringActivities(B activityRequest) {
        this.checkRecurringActivitiesFieldsThroughActivityRequest(activityRequest);
        
        List<A> activities = new ArrayList<>();
        activities.add(this.mapToEntity(activityRequest));

        // Getting first activity time diff in hours
        LocalDateTime startDateTime = LocalDateTime.of(activityRequest.getDateStart(), activityRequest.getHourStart());
        LocalDateTime endDateTime = LocalDateTime.of(activityRequest.getDateEnd(), activityRequest.getHourEnd());
        Long diffInHours = ChronoUnit.HOURS.between(startDateTime, endDateTime);

        // Setting up loop variables
        Iterator<Integer> daysIterator = activityRequest.getDaysToRepeat().iterator();
        LocalDateTime currentDate = startDateTime;
        LocalDateTime limitDate = LocalDateTime.of(activityRequest.getRepeatUntil(), activityRequest.getHourStart());

        while (currentDate.isBefore(limitDate)) {
            currentDate = findNextDate(currentDate, DayOfWeek.of(daysIterator.next()));

            if(currentDate.isAfter(limitDate)) {
                break;
            }

            activities.add(
                this.mapToEntityDiffDates(
                    Date.valueOf(currentDate.toLocalDate()), 
                    Date.valueOf(currentDate.plusHours(diffInHours).toLocalDate()), 
                    activityRequest
                )
            );

            if(!daysIterator.hasNext()) {
                daysIterator = activityRequest.getDaysToRepeat().iterator();
            }
        }

        RecurringActivity recurringActivity = this.recurringActivityService.createRecurringActivity(new RecurringActivity());
        activities.forEach(activity -> activity.setRecurringActivityId(recurringActivity.getId()));

        return this.createMultipleActivities(activities).get(0);
	}

    /* UTILS */
    private LocalDateTime findNextDate(LocalDateTime currentDate, DayOfWeek dayOfWeek) {
        LocalDateTime nextDate = currentDate;

        if (currentDate.getDayOfWeek() == dayOfWeek) {
            nextDate = currentDate.plusDays(1);
        }
        while (nextDate.getDayOfWeek() != dayOfWeek) {
            nextDate = nextDate.plusDays(1);
        }

        return nextDate;
    }

    private void checkRecurringActivitiesFieldsThroughActivityRequest(ActivityRequest activityRequest) {
        if (activityRequest.getDaysToRepeat().isEmpty() || activityRequest.getRepeatUntil() == null) {
            throw new RecurringActivityException(RecurringActivityType.FIELD);
        }

        LocalDate startDate = activityRequest.getDateStart();
        LocalDate endDate = activityRequest.getRepeatUntil();

        if (startDate.isAfter(endDate)) {
            throw new RecurringActivityException(RecurringActivityType.INTERVAL);
        }

        // Check if there is no days lesser than 1 and greater than 7. Valid interval: [1,7]. 1 is Monday and 7 is Sunday.
        Long daysLesserThan1 = activityRequest.getDaysToRepeat()
            .stream()
            .filter(day -> day < 1)
            .count();

        Long daysGreaterThan7 = activityRequest.getDaysToRepeat()
            .stream()
            .filter(day -> day > 7)
            .count();

        if (daysLesserThan1 > 0 || daysGreaterThan7 > 0) {
            throw new RecurringActivityException(RecurringActivityType.DAY_INDEX);
        }
    }
}
