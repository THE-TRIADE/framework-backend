package imd.ufrn.framework.controller;

import java.util.List;

public interface GroupUserDependentController <T, GroupRequest, GroupResponse> {

    public List<GroupResponse> getAllGroupUserDependents();

    public GroupResponse findGroupUserDependentById( Long groupUserDependentId);

    public GroupResponse createGroupUserDependent( GroupRequest newGroupUserDependent);

    public void deleteAllGroupUserDependents();

    public void deleteGroupUserDependentById(Long groupUserDependentId);
}
