package imd.ufrn.instancestudentroutine.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.controller.GroupUserDependentController;
import imd.ufrn.instancestudentroutine.model.GroupUserDependentStandard;
import imd.ufrn.instancestudentroutine.model.api.request.GroupUserDependentStandardRequest;
import imd.ufrn.instancestudentroutine.model.api.response.GroupUserDependentStandardResponse;
import imd.ufrn.instancestudentroutine.service.GroupUserDependentServiceImpl;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("group-user-dependent")
public class GroupUserDependentControllerImpl implements GroupUserDependentController<GroupUserDependentStandard, GroupUserDependentStandardRequest, GroupUserDependentStandardResponse> {
    @Autowired
    private GroupUserDependentServiceImpl groupUserDependentService;
    
    @GetMapping
    public List<GroupUserDependentStandardResponse> getAllGroupUserDependents(@RequestParam Optional<String> groupType) {
        return
        groupType.map(
            type -> this.groupUserDependentService.findAllByGroupType(type)
        )
        .orElse(this.groupUserDependentService.findAll());
    }

    @Override
    @GetMapping("{groupUserDependentId}")
    public GroupUserDependentStandardResponse findGroupUserDependentById(@PathVariable Long groupUserDependentId) {
        return this.groupUserDependentService.findGroupUserDependentById(groupUserDependentId);
    }

    @Override
    @PostMapping
    public GroupUserDependentStandardResponse createGroupUserDependent(@RequestBody @Valid GroupUserDependentStandardRequest newGroupUserDependent) {
        return this.groupUserDependentService.createGroupUserDependent(newGroupUserDependent);
    }

    @Override
    @DeleteMapping
    public void deleteAllGroupUserDependents() {
        this.groupUserDependentService.deleteAllGroupUserDependents();
    }

    @Override
    @DeleteMapping("{groupUserDependentId}")
    public void deleteGroupUserDependentById(@PathVariable Long groupUserDependentId) {
        this.groupUserDependentService.deleteGroupUserDependentById(groupUserDependentId);
    }

    @Override
    public List<GroupUserDependentStandardResponse> getAllGroupUserDependents() {
        return this.groupUserDependentService.findAll();
    } 
}
