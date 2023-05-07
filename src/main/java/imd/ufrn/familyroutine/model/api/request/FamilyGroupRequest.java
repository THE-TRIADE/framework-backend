package imd.ufrn.familyroutine.model.api.request;

import java.util.List;

import imd.ufrn.familyroutine.model.Dependent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FamilyGroupRequest {
    @NotEmpty
    private String name;
    @NotNull
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
