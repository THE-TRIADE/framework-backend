package imd.ufrn.framework.model;

import org.springframework.data.annotation.Id;

public class DependentGroup {
    @Id
    private Long groupId;
    @Id
    private Long dependentId;

    public DependentGroup() {
    }
    
    public DependentGroup(Long groupId, Long dependentId) {
        this.groupId = groupId;
        this.dependentId = dependentId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDependentId() {
        return dependentId;
    }

    public void setDependentId(Long dependentId) {
        this.dependentId = dependentId;
    }

    
}
