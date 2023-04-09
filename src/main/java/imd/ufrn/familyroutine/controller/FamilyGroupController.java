package imd.ufrn.familyroutine.controller;

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

import imd.ufrn.familyroutine.model.FamilyGroup;
import imd.ufrn.familyroutine.service.FamilyGroupService;

@CrossOrigin
@RestController
@RequestMapping("familyGroup")
public class FamilyGroupController {
    @Autowired
    private FamilyGroupService familyGroupService;
    
    @GetMapping
    public List<FamilyGroup> getAllFamilyGroups() {
        return this.familyGroupService.findAll();
    }

    @GetMapping("{familyGroupId}")
    public FamilyGroup findFamilyGroupById(@PathVariable Long familyGroupId) {
        return this.familyGroupService.findFamilyGroupById(familyGroupId);
    }

    @PostMapping
    public FamilyGroup createFamilyGroup(@RequestBody FamilyGroup newFamilyGroup) {
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
