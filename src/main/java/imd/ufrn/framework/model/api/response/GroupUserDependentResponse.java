package imd.ufrn.framework.model.api.response;

import java.util.List;

import imd.ufrn.framework.model.Dependent;

public abstract class GroupUserDependentResponse {
    protected Long id;
    protected String name;
    protected List<? extends Dependent> dependents;

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
    public List<? extends Dependent> getDependents() {
        return dependents;
    }
    public void setDependents(List<? extends Dependent> dependents) {
        this.dependents = dependents;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GroupUserDependentResponse other = (GroupUserDependentResponse) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
