package imd.ufrn.familyroutine.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import imd.ufrn.familyroutine.model.FamilyGroup;
import imd.ufrn.familyroutine.model.api.request.FamilyGroupRequest;
import imd.ufrn.familyroutine.model.api.response.FamilyGroupResponse;
import imd.ufrn.familyroutine.service.FamilyGroupService;

@Component
public class FamilyGroupMapper {
    @Lazy
    @Autowired
    private FamilyGroupService familyGroupService;

    public FamilyGroup mapFamilyGroupRequestToFamilyGroup(FamilyGroupRequest familyGroupRequest){
        FamilyGroup familyGroup = new FamilyGroup();
        familyGroup.setName(familyGroupRequest.getName());
        return familyGroup;
    }

    public FamilyGroupResponse mapFamilyGroupToFamilyGroupResponse(FamilyGroup familyGroup){
        FamilyGroupResponse familyGroupResponse = new FamilyGroupResponse();
        familyGroupResponse.setId(familyGroup.getId());
        familyGroupResponse.setName(familyGroup.getName());
        familyGroupResponse.setDependents(familyGroupService.getFamilyGroupDependentsByFamilyGroupId(familyGroup.getId()));

        return familyGroupResponse;
    }

}
