package imd.ufrn.instancestudentroutine.model.api.request;

import imd.ufrn.framework.model.api.request.ActivityRequest;

public class ActivityWithCourseRequest extends ActivityRequest {

    private Long courseId;

    public ActivityWithCourseRequest() {
        
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
