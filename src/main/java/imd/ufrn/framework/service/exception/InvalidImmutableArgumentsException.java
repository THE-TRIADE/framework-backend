package imd.ufrn.framework.service.exception;

import imd.ufrn.framework.FrameworkRoutineException;

public class InvalidImmutableArgumentsException extends FrameworkRoutineException {
    private String nonModifiableAtributeOne;
    private String nonModifiableAtributeTwo;

    public InvalidImmutableArgumentsException (String nonModifiableAtributeOne, String nonModifiableAtributeTwo) {
        this.nonModifiableAtributeOne = nonModifiableAtributeOne;
        this.nonModifiableAtributeTwo = nonModifiableAtributeTwo;
    }

    @Override
    public String getMessage() {
        return "Atributes: " + nonModifiableAtributeOne + ", and " + nonModifiableAtributeTwo + " are invalid";
    }

}
