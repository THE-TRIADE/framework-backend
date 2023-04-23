package imd.ufrn.familyroutine.service;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.Dependent;
import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.model.Person;
import imd.ufrn.familyroutine.model.RecurringActivity;
import imd.ufrn.familyroutine.model.api.ActivityMapper;
import imd.ufrn.familyroutine.model.api.request.ActivityRequest;
import imd.ufrn.familyroutine.service.exception.RecurringActivityException;
import imd.ufrn.familyroutine.service.exception.RecurringActivityType;

@Service
public class ServiceMediator {
    @Lazy
    @Autowired
    private GuardianService guardianService;
    @Lazy
    @Autowired
    private DependentService dependentService;
    @Lazy
    @Autowired
    private PersonService personService;
    @Lazy
    @Autowired
    private ActivityService activityService;
    @Autowired
    private RecurringActivityService recurringActivityService;
    @Lazy
    @Autowired
    private ActivityMapper activityMapper;

    @Transactional
    public Dependent createDependent(Dependent newDependent) {
        Person personCreated = this.personService.createPerson(newDependent);
        newDependent.setId(personCreated.getId());
        this.dependentService.createDependent(newDependent);
        return newDependent;
    }

    @Transactional
    public void deleteAllDependents() {
        List<Dependent> dependents = this.dependentService.findAll();
        this.personService.deleteAllDependents(dependents);
    }

    public void deleteDependentById(Long dependentId) {
        this.personService.deletePersonById(dependentId);
    }

    @Transactional
    public Guardian createGuardian(Guardian newGuardian) {
        Person personCreated = this.personService.createPerson(newGuardian);
        newGuardian.setId(personCreated.getId());
        this.guardianService.createGuardian(newGuardian);
        return newGuardian;
    }

    @Transactional
    public void deleteAllGuardians() {
        List<Guardian> guardians = this.guardianService.findAll();
        this.personService.deleteAllGuardians(guardians);
    }

    public void deleteGuardianById(Long guardianId) {
        this.personService.deletePersonById(guardianId);
    }

    @Transactional
	public Activity createRecurringActivities(ActivityRequest activityRequest) {
        this.checkRecurringActivitiesFieldsThroughActivityRequest(activityRequest);
        
        List<Activity> activities = new ArrayList<>();
        activities.add(this.activityMapper.mapActivityRequestToActivity(activityRequest));

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

            Activity newActivity = new Activity();
            newActivity.setName(activityRequest.getName());
            newActivity.setDateStart(Date.valueOf(currentDate.toLocalDate()));
            newActivity.setDateEnd(Date.valueOf(currentDate.plusHours(diffInHours).toLocalDate()));
            newActivity.setHourStart(Time.valueOf(activityRequest.getHourStart()));
            newActivity.setHourEnd(Time.valueOf(activityRequest.getHourEnd()));
            newActivity.setDependentId(activityRequest.getDependentId());
            newActivity.setCurrentGuardian(activityRequest.getCurrentGuardian());
            newActivity.setActor(activityRequest.getActor());
            newActivity.setCreatedBy(activityRequest.getCreatedBy());
            newActivity.setState(activityRequest.getState());

            activities.add(newActivity);

            if(!daysIterator.hasNext()) {
                daysIterator = activityRequest.getDaysToRepeat().iterator();
            }
        }

        RecurringActivity recurringActivity = this.recurringActivityService.createRecurringActivity(new RecurringActivity());
        activities.forEach(activity -> activity.setRecurringActivityId(recurringActivity.getId()));

        return this.activityService.createMultipleActivities(activities).get(0);
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
