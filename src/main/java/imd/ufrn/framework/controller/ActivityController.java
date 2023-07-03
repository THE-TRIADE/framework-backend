package imd.ufrn.framework.controller;

import java.util.List;
import java.util.Optional;

/**
 * @param A should be your ActivityRequest class
 * @param B should be your FinishActivityRequest class
 * @param C should be your ActivityResponse class
 */
public interface ActivityController<A, B, C> {
    public List<C> getAllActivities(Optional<Long> dependentId);
    public C findActivityById(Long activityId);
    public C createActivity(A activity);
    public C createActivity(Long activityId, B finishActivityRequest);
    public void deleteActivity();
    public void deleteActivityById(Long activityId);
}
