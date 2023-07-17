package imd.ufrn.instancestudentroutine.model;

import imd.ufrn.framework.model.ActivityAbstract;

public class ActivityWithCourse extends ActivityAbstract {

    private Double grade;
    private Long courseId;

    public ActivityWithCourse() {

    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public Double getGrade() {
        return grade;
    }
    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
