package imd.ufrn.familyroutine.model.api;

import org.springframework.beans.factory.annotation.Autowired;

import imd.ufrn.familyroutine.model.FamilyGroup;
import imd.ufrn.familyroutine.model.api.request.FamilyGroupRequest;
import imd.ufrn.familyroutine.model.api.response.FamilyGroupResponse;
import imd.ufrn.familyroutine.service.FamilyGroupService;

public class FamilyGroupMapper {
    
    @Autowired
    private FamilyGroupService familyGroupService;

    public FamilyGroup mapFamilyGroupRequestToFamilyGroup(FamilyGroupRequest familyGroupRequest){
        FamilyGroup familyGroup = new FamilyGroup();
        familyGroup.setName(familyGroupRequest.getName());
        return familyGroup;
    }

    public FamilyGroupResponse mapFamilyGroupToFamilyGroupResponse(FamilyGroup familyGroup){
        FamilyGroupResponse familyGroupResponse = new FamilyGroupResponse();
        familyGroupResponse.setDependents(familyGroupService.getFamilyGroupDependentsByFamilyGroupId(familyGroup.getId()));

        return familyGroupResponse;
    }

}
