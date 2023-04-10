package imd.ufrn.familyroutine.service.exception;

import imd.ufrn.familyroutine.FamilyRoutineException;

public class InvalidUserException extends FamilyRoutineException {
    @Override
    public String getMessage() {
        return "Invalid user or password.";
    }
}
