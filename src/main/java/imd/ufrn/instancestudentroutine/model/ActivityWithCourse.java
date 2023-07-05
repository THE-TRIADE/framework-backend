package imd.ufrn.instancestudentroutine.model;

import imd.ufrn.framework.model.ActivityAbstract;

public class ActivityWithCourse extends ActivityAbstract {

    private Long courseId;

    public ActivityWithCourse() {

    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
