package imd.ufrn.instancestudentroutine.model.api.request;

import imd.ufrn.framework.model.api.request.FinishActivityRequest;

public class FinishActivityWithCourseRequest extends FinishActivityRequest {

    private Double grade;

    public FinishActivityWithCourseRequest() {
        
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
