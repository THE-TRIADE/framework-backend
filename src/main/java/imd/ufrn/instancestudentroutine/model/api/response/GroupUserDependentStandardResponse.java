package imd.ufrn.instancestudentroutine.model.api.response;

import java.util.List;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;

public class GroupUserDependentStandardResponse extends GroupUserDependentResponse {
    protected List<User> users;
    private String groupType;

    public GroupUserDependentStandardResponse(Long id, String name, List<? extends Dependent> dependents, List<User> users) {
        this.id = id;
        this.name = name;
        this.dependents = dependents;
        this.users = users;
    }

    public GroupUserDependentStandardResponse() {}

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
