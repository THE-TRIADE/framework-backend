package imd.ufrn.instancefamilyroutine.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.api.GroupUserDependentMapper;
import imd.ufrn.framework.service.GroupUserDependentService;
import imd.ufrn.instancefamilyroutine.model.GroupUserDependentStandard;
import imd.ufrn.instancefamilyroutine.model.api.request.GroupUserDependentStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.response.GroupUserDependentStandardResponse;

@Component
public class GroupUserDependentMapperImpl extends GroupUserDependentMapper<GroupUserDependentStandard, GroupUserDependentStandardRequest, GroupUserDependentStandardResponse> {
    @Lazy
    @Autowired
    private GroupUserDependentService<GroupUserDependentStandard, GroupUserDependentStandardRequest, GroupUserDependentStandardResponse> groupUserDependentService;

    @Override
    public GroupUserDependentStandard mapGroupUserDependentRequestToGroupUserDependent(GroupUserDependentStandardRequest groupUserDependentRequest)
    {
        GroupUserDependentStandard groupUserDependent = new GroupUserDependentStandard();
        groupUserDependent.setName(groupUserDependentRequest.getName());
        return groupUserDependent;
    }

    @Override
    public GroupUserDependentStandardResponse mapGroupUserDependentToGroupUserDependentResponse(GroupUserDependentStandard groupUserDependent)
    {
        GroupUserDependentStandardResponse groupUserDependentResponse = new GroupUserDependentStandardResponse();
        groupUserDependentResponse.setId(groupUserDependent.getId());
        groupUserDependentResponse.setName(groupUserDependent.getName());
        groupUserDependentResponse.setDependents(groupUserDependentService.getDependentsByGroupUserDependentId(groupUserDependent.getId()));

        return groupUserDependentResponse;
    }

}
