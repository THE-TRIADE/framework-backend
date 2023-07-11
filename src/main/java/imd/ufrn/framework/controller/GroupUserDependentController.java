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

import imd.ufrn.framework.model.api.request.GroupUserDependentRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.service.GroupUserDependentService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("group-user-dependent")
public class GroupUserDependentController {
    @Autowired
    private GroupUserDependentService groupUserDependentService;
    
    @GetMapping
    public List<GroupUserDependentResponse> getAllGroupUserDependents() {
        return this.groupUserDependentService.findAll();
    }

    @GetMapping("{groupUserDependentId}")
    public GroupUserDependentResponse findGroupUserDependentById(@PathVariable Long groupUserDependentId) {
        return this.groupUserDependentService.findGroupUserDependentById(groupUserDependentId);
    }

    @PostMapping
    public GroupUserDependentResponse createGroupUserDependent(@RequestBody @Valid GroupUserDependentRequest newGroupUserDependent) {
        return this.groupUserDependentService.createGroupUserDependent(newGroupUserDependent);
    }

    @DeleteMapping
    public void deleteAllGroupUserDependents() {
        this.groupUserDependentService.deleteAllGroupUserDependents();
    }

    @DeleteMapping("{groupUserDependentId}")
    public void deleteGroupUserDependentById(@PathVariable Long groupUserDependentId) {
        this.groupUserDependentService.deleteGroupUserDependentById(groupUserDependentId);
    } 

}
