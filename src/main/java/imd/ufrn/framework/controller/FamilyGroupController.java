package imd.ufrn.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.model.api.request.FamilyGroupRequest;
import imd.ufrn.framework.model.api.response.FamilyGroupResponse;
import imd.ufrn.framework.service.FamilyGroupService;

@CrossOrigin
@RestController
@RequestMapping("familyGroup")
public class FamilyGroupController {
    @Autowired
    private FamilyGroupService familyGroupService;
    
    @GetMapping
    public List<FamilyGroupResponse> getAllFamilyGroups() {
        return this.familyGroupService.findAll();
    }

    @GetMapping("{familyGroupId}")
    public FamilyGroupResponse findFamilyGroupById(@PathVariable Long familyGroupId) {
        return this.familyGroupService.findFamilyGroupById(familyGroupId);
    }

    @PostMapping
    public FamilyGroupResponse createFamilyGroup(@RequestBody FamilyGroupRequest newFamilyGroup) {
        return this.familyGroupService.createFamilyGroup(newFamilyGroup);
    }

    @DeleteMapping
    public void deleteAllFamilyGroups() {
        this.familyGroupService.deleteAllFamilyGroups();
    }

    @DeleteMapping("{familyGroupId}")
    public void deleteFamilyGroupById(@PathVariable Long familyGroupId) {
        this.familyGroupService.deleteFamilyGroupById(familyGroupId);
    } 

}
