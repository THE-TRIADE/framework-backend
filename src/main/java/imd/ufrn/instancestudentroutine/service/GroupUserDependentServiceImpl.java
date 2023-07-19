package imd.ufrn.instancestudentroutine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.DependentGroup;
import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.api.UserMapper;
import imd.ufrn.framework.model.api.request.RelationRequest;
import imd.ufrn.framework.service.GroupUserDependentService;
import imd.ufrn.framework.service.UserService;
import imd.ufrn.instancestudentroutine.model.GroupUserDependentStandard;
import imd.ufrn.instancestudentroutine.model.UserInGroup;
import imd.ufrn.instancestudentroutine.model.api.request.GroupUserDependentStandardRequest;
import imd.ufrn.instancestudentroutine.model.api.response.GroupUserDependentStandardResponse;

@Service
public class GroupUserDependentServiceImpl extends GroupUserDependentService<GroupUserDependentStandard, GroupUserDependentStandardRequest, GroupUserDependentStandardResponse> {
	@Autowired
    protected UserInGroupService userInGroupService;
    @Lazy
    @Autowired
    protected UserService userService;
    @Autowired
    protected UserMapper userMapper;

    public List<GroupUserDependentStandardResponse> findAllByGroupType(String type) {
        return 
        this.findAll()
            .stream()
            .filter(group -> group.getGroupType().equalsIgnoreCase(type))
            .toList();
    }

    public List<User> getUsersByGroupUserDependentId(Long groupUserDependentId){
        return userInGroupService
            .findUserInGroupsByGroupId(groupUserDependentId)
            .stream()
            .map(UserInGroup::getUserId)
            .map(this.userService::findUserById)
            .toList();
    }

    @Override
    public List<GroupUserDependentStandardResponse> findGroupsByUserId(Long userId){
        List<GroupUserDependentStandardResponse> groups = new ArrayList<GroupUserDependentStandardResponse>();

        User user = this.userService.findUserById(userId);

        userInGroupService.findUserInGroupsByUserId(userId).forEach(userInGroup -> {
            groups.add(this.findGroupUserDependentById(userInGroup.getGroupId()));
        });

        Function<String, String> findGroupType = role -> role.equalsIgnoreCase("TEACHER") ? "CLASS" : "FAMILY";

        List<GroupUserDependentStandardResponse> allGroups = this.findAllByGroupType(findGroupType.apply(user.getRole().toString()));

        relationService.findRelationsByUserId(userId).forEach(relation -> {
            groups.add(
                allGroups.stream()
                    .filter(group -> group.getDependents().stream().map(Dependent::getId).toList().contains(relation.getDependentId()))
                    .findAny()
                    .get()
            );
        });

        return groups.stream().distinct().toList();
    }

    @Override
    @Transactional
    public GroupUserDependentStandardResponse createGroupUserDependent(GroupUserDependentStandardRequest groupUserDependentRequest) {
        GroupUserDependentStandard groupUserDependent = this.groupUserDependentRepository.save(groupUserDependentMapper.mapGroupUserDependentRequestToGroupUserDependent(groupUserDependentRequest));

        // Dependents processing
        List<? extends Dependent> dependents = groupUserDependentRequest
            .getDependents()
            .stream()
            .map(dependent -> {
                if(dependent.getId() == null) {
                    return this.dependentService.createDependent(dependent);
                }
                return dependent;
            })
            .map(dependent -> {
                // Creates new DependentGroup
                DependentGroup newDependentGroup = new DependentGroup();
                newDependentGroup.setDependentId(dependent.getId());
                newDependentGroup.setGroupId(groupUserDependent.getId());
                this.dependentGroupService.createDependentGroup(newDependentGroup);
                return dependent;
            })
            .toList();

            // User processing
            List<User> users = groupUserDependentRequest
            .getUsers()
            .stream()
            .map(user -> {
                if(user.getId() == null) {
                    return this.userService.createUser(user);
                }
                return user;
            })
            .map(user -> {
                // Creates new UserInGroup
                UserInGroup newUserInGroup = new UserInGroup();
                newUserInGroup.setUserId(user.getId());
                newUserInGroup.setGroupId(groupUserDependent.getId());
                this.userInGroupService.createUserInGroup(newUserInGroup);
                return user;
            })
            .toList();

            // Creates new relations if wanted
            if(groupUserDependentRequest.getUserRole() != null){
                users.stream().forEach( user -> {
                    dependents.stream().forEach(dependent ->{

                        RelationRequest newRelation = new RelationRequest();
                        newRelation.setDependentId(dependent.getId());
                        newRelation.setUserId(user.getId());
                        newRelation.setUserRole(groupUserDependentRequest.getUserRole());
                        this.relationService.createRelation(newRelation);
                    });
                });
            }
            
        return this.groupUserDependentMapper.mapGroupUserDependentToGroupUserDependentResponse(groupUserDependent);
    }

    
}
