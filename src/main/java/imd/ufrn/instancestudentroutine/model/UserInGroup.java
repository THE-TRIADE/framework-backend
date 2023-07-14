package imd.ufrn.instancestudentroutine.model;

import org.springframework.data.annotation.Id;

public class UserInGroup {
    @Id
    private Long groupId;
    @Id
    private Long userId;

    public UserInGroup() {
    }
    
    public UserInGroup(Long groupId, Long dependentId) {
        this.groupId = groupId;
        this.userId = dependentId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long dependentId) {
        this.userId = dependentId;
    }

    
}
