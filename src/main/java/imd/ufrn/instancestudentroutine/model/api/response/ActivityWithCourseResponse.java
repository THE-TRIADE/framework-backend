package imd.ufrn.instancestudentroutine.model.api.response;

import imd.ufrn.framework.model.api.response.ActivityResponse;

public class ActivityWithCourseResponse extends ActivityResponse {

    private Long courseId;
    private String courseName;

    public ActivityWithCourseResponse() {
        
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
