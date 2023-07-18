package imd.ufrn.instancestudentroutine.model.api.request;

import java.util.List;

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.UserRole;
import imd.ufrn.framework.model.api.request.GroupUserDependentRequest;
import imd.ufrn.instancestudentroutine.model.DependentStudent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GroupUserDependentStandardRequest extends GroupUserDependentRequest {
    @NotNull
    @Valid
    protected List<User> users;
    @NotEmpty
    private String groupType;

    public GroupUserDependentStandardRequest(String name, UserRole userRole, Long userId, List<DependentStudent> dependents,  List<User> users) {
        this.name = name;
        this.userRole = userRole;
        this.userId = userId;
        this.dependents = dependents;
        this.users = users;
    }

    public GroupUserDependentStandardRequest() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
