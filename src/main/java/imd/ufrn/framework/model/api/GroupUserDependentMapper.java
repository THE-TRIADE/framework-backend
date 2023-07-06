package imd.ufrn.framework.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.GroupUserDependent;
import imd.ufrn.framework.model.api.request.GroupUserDependentRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.service.GroupUserDependentService;

@Component
public class GroupUserDependentMapper {
    @Lazy
    @Autowired
    private GroupUserDependentService groupUserDependentService;

    public GroupUserDependent mapGroupUserDependentRequestToGroupUserDependent(GroupUserDependentRequest groupUserDependentRequest){
        GroupUserDependent groupUserDependent = new GroupUserDependent();
        groupUserDependent.setName(groupUserDependentRequest.getName());
        return groupUserDependent;
    }

    public GroupUserDependentResponse mapGroupUserDependentToGroupUserDependentResponse(GroupUserDependent groupUserDependent){
        GroupUserDependentResponse groupUserDependentResponse = new GroupUserDependentResponse();
        groupUserDependentResponse.setId(groupUserDependent.getId());
        groupUserDependentResponse.setName(groupUserDependent.getName());
        groupUserDependentResponse.setDependents(groupUserDependentService.getGroupUserDependentDependentsByGroupUserDependentId(groupUserDependent.getId()));

        return groupUserDependentResponse;
    }

}
