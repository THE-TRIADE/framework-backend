package imd.ufrn.familyroutine.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.service.exception.ActivityIntervalException;
import imd.ufrn.familyroutine.service.exception.ActivityIntervalType;

@Service
public class ValidationService {
    
    public Activity validActivityOrError(Activity activity) {
        LocalDate dateStart = activity.getDateStart().toLocalDate();
        LocalDate dateEnd = activity.getDateEnd().toLocalDate();
        LocalTime hourStart = activity.getHourStart().toLocalTime();
        LocalTime hourEnd = activity.getHourEnd().toLocalTime();


        if (dateStart.isAfter(dateEnd)) {
            throw new ActivityIntervalException (dateStart, dateEnd, ActivityIntervalType.DATE);
        }
        else if (dateStart.isEqual(dateEnd)) {
            if (hourStart.isAfter(hourEnd)) {
                throw new ActivityIntervalException (hourStart, hourEnd, ActivityIntervalType.TIME);
            }
        }

        return activity;
    }
}
