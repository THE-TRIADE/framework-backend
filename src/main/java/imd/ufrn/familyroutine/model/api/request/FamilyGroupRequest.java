package imd.ufrn.familyroutine.model.api.request;

import java.util.List;

import imd.ufrn.familyroutine.model.Dependent;
import jakarta.validation.constraints.NotEmpty;

public class FamilyGroupRequest {
    @NotEmpty
    private String name;

    private List<Dependent> dependents;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
}
