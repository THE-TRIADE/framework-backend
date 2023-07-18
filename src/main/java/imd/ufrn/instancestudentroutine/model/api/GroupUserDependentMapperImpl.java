package imd.ufrn.instancestudentroutine.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.api.GroupUserDependentMapper;
import imd.ufrn.instancestudentroutine.model.GroupUserDependentStandard;
import imd.ufrn.instancestudentroutine.model.api.request.GroupUserDependentStandardRequest;
import imd.ufrn.instancestudentroutine.model.api.response.GroupUserDependentStandardResponse;
import imd.ufrn.instancestudentroutine.service.GroupUserDependentServiceImpl;

@Component
public class GroupUserDependentMapperImpl extends GroupUserDependentMapper<GroupUserDependentStandard, GroupUserDependentStandardRequest, GroupUserDependentStandardResponse> {
    @Lazy
    @Autowired
    private GroupUserDependentServiceImpl groupUserDependentService;

    @Override
    public GroupUserDependentStandard mapGroupUserDependentRequestToGroupUserDependent(GroupUserDependentStandardRequest groupUserDependentRequest)
    {
        GroupUserDependentStandard groupUserDependent = new GroupUserDependentStandard();
        groupUserDependent.setName(groupUserDependentRequest.getName());
        groupUserDependent.setGroupType(groupUserDependentRequest.getGroupType());
        return groupUserDependent;
    }

    @Override
    public GroupUserDependentStandardResponse mapGroupUserDependentToGroupUserDependentResponse(GroupUserDependentStandard groupUserDependent)
    {
        GroupUserDependentStandardResponse groupUserDependentResponse = new GroupUserDependentStandardResponse();
        groupUserDependentResponse.setId(groupUserDependent.getId());
        groupUserDependentResponse.setName(groupUserDependent.getName());
        groupUserDependentResponse.setGroupType(groupUserDependent.getGroupType());
        groupUserDependentResponse.setDependents(groupUserDependentService.getDependentsByGroupUserDependentId(groupUserDependent.getId()));
        groupUserDependentResponse.setUsers(groupUserDependentService.getUsersByGroupUserDependentId(groupUserDependent.getId()));

        return groupUserDependentResponse;
    }

}
