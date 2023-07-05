package imd.ufrn.framework.service.exception;

import imd.ufrn.framework.FrameworkRoutineException;

public class InvalidUserException extends FrameworkRoutineException {
    @Override
    public String getMessage() {
        return "Invalid user or password.";
    }
}
