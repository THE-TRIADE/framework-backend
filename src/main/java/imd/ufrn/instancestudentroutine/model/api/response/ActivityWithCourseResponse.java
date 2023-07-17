package imd.ufrn.instancestudentroutine.model.api.response;

import imd.ufrn.framework.model.api.response.ActivityResponse;

public class ActivityWithCourseResponse extends ActivityResponse {

    private Long courseId;
    private String courseName;
    private Double grade;

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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
    
}
