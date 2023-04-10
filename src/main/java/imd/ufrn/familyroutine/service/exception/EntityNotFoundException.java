package imd.ufrn.familyroutine.service.exception;

import imd.ufrn.familyroutine.FamilyRoutineException;

public class EntityNotFoundException extends FamilyRoutineException {
    private Long entityId;
    private Class<? extends Object> entityClass;

    public EntityNotFoundException (Long entityId, Class<? extends Object> entityClass) {
        this.entityId = entityId;
        this.entityClass = entityClass;
    }

    public EntityNotFoundException (Long entityId, Throwable cause) {
        super(cause);
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return "Could not found " + entityClass.getSimpleName() + " with id " + entityId  + ".";
    }

}
