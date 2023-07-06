package imd.ufrn.framework.model.api.request;

import java.util.List;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.GuardianRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GroupUserDependentRequest {
    @NotEmpty
    private String name;
    @NotNull
    private GuardianRole userRole;
    @NotNull
    private Long userId;
    @NotNull
    private List<Dependent> dependents;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public GuardianRole getUserRole() {
        return userRole;
    }
    public void setUserRole(GuardianRole userRole) {
        this.userRole = userRole;
    }
    public List<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
