package imd.ufrn.familyroutine.model.api.response;

import java.util.List;

import imd.ufrn.familyroutine.model.Dependent;

public class FamilyGroupResponse {
    private Long id;
    private String name;
    private List<Dependent> dependents;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
