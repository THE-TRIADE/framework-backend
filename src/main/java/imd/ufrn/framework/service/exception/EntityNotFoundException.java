package imd.ufrn.framework.service.exception;

import imd.ufrn.framework.FrameworkRoutineException;

public class EntityNotFoundException extends FrameworkRoutineException {
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
