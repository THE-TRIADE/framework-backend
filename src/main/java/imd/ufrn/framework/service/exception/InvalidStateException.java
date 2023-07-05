package imd.ufrn.framework.service.exception;

import java.util.List;
import java.util.stream.Collectors;

import imd.ufrn.framework.FrameworkRoutineException;
import imd.ufrn.framework.model.ActivityAbstract;

public class InvalidStateException extends FrameworkRoutineException {
    private Object entity;
    List<Object> validStates;

    public InvalidStateException(Object entity, List<Object> validStates) {
        this.entity = entity;
        this.validStates = validStates;
    }

    @Override
    public String getMessage() {
        return "The " + entity.getClass().getSimpleName() + " of id " + handleEntityId() + " should be " + handleValidStates() + ".";
    }
    
    private String handleValidStates() {
        List<String> statesAsStrings =
            this.validStates
                .stream()
                .map(state -> ((Enum<?>) state).name())
                .collect(Collectors.toList());

        return String.join(" or ", statesAsStrings);
    }

    private Long handleEntityId() {
        if (entity instanceof ActivityAbstract) {
            return ((ActivityAbstract) entity).getId();
        }
        return 0L;
    }
}
