package imd.ufrn.framework.model.api.request;

import java.util.List;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GroupUserDependentRequest {
    @NotEmpty
    private String name;
    @NotNull
    private UserRole userRole;
    @NotNull
    private Long userId;
    @NotNull
    private List<? extends Dependent> dependents;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public List<? extends Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<? extends Dependent> dependents) {
        this.dependents = dependents;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}