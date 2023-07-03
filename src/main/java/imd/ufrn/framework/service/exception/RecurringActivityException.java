package imd.ufrn.framework.service.exception;

import imd.ufrn.framework.FrameworkRoutineException;

public class RecurringActivityException extends FrameworkRoutineException {
    private RecurringActivityType type;

    public RecurringActivityException (RecurringActivityType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        String message = "";

        if(type == RecurringActivityType.FIELD) {
            message += "'repeat' field is true. Check if both 'repeatUntil' and 'daysToRepeat' fields are filled correctly.";
        }
        else if (type == RecurringActivityType.DAY_INDEX) {
            message += "'daysToRepeat' has a number either lesser than one or greater than seven. Please try again.";
        }
        else if (type == RecurringActivityType.INTERVAL) {
            message += "'repeatUntil' date occurs before the first activity date. Please try again.";
        }
        
        return message;
    }
    
}
