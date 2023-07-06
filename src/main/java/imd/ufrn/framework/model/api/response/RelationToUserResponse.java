package imd.ufrn.framework.model.api.response;

import java.util.List;

import imd.ufrn.framework.model.UserRole;

public class RelationToUserResponse {
    private Long id;
    private List<Integer> daysOfWeek;
    private UserRole userRole;
    private Long dependentId;
    private String dependentName;
    
    public RelationToUserResponse() {}
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
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
