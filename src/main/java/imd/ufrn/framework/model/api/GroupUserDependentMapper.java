package imd.ufrn.framework.model.api;

import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.GroupUserDependent;
import imd.ufrn.framework.model.api.request.GroupUserDependentRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;

@Component
public abstract class GroupUserDependentMapper <T extends GroupUserDependent, GroupRequest extends GroupUserDependentRequest, GroupResponse extends GroupUserDependentResponse> {

    abstract public T mapGroupUserDependentRequestToGroupUserDependent(GroupRequest groupUserDependentRequest);

    abstract public GroupResponse mapGroupUserDependentToGroupUserDependentResponse(T groupUserDependent);

}
