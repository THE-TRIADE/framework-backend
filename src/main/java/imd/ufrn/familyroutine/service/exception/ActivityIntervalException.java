package imd.ufrn.familyroutine.service.exception;

import java.time.LocalDate;
import java.time.LocalTime;

import imd.ufrn.familyroutine.FamilyRoutineException;

public class ActivityIntervalException extends FamilyRoutineException {
    private Object startInterval;
    private Object endInterval;
    private ActivityIntervalType type;

    public ActivityIntervalException (Object startInterval, Object endInterval, ActivityIntervalType type) {
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.type = type;
    }

    public ActivityIntervalException (Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        String message = "";
        if (type == ActivityIntervalType.DATE) {
            message += "The start Date occurs after end Date. ";
            message += ((LocalDate) startInterval) + " > " + ((LocalDate) endInterval);
        }
        else if (type == ActivityIntervalType.TIME) {
            message += "The start Time occurs after end Time. ";
            message += ((LocalTime) startInterval) + " > " + ((LocalTime) endInterval);
        }
        return "Invalid activity interval. " + message + ".";
    }
}
