package imd.ufrn.framework.model.api.response;

import java.util.List;

import imd.ufrn.framework.model.GuardianRole;

public class GuardToGuardianResponse {
    private Long id;
    private List<Integer> daysOfWeek;
    private GuardianRole guardianRole;
    private Long dependentId;
    private String dependentName;
    
    public GuardToGuardianResponse() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }
    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    public GuardianRole getGuardianRole() {
        return guardianRole;
    }
    public void setGuardianRole(GuardianRole guardianRole) {
        this.guardianRole = guardianRole;
    }
    public Long getDependentId() {
        return dependentId;
    }
    public void setDependentId(Long dependentId) {
        this.dependentId = dependentId;
    }
    public String getDependentName() {
        return dependentName;
    }
    public void setDependentName(String dependentName) {
        this.dependentName = dependentName;
    }

    
}
