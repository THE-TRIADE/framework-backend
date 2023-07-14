package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.DependentGroup;
import imd.ufrn.framework.model.GroupUserDependent;
import imd.ufrn.framework.model.api.GroupUserDependentMapper;
import imd.ufrn.framework.model.api.request.GroupUserDependentRequest;
import imd.ufrn.framework.model.api.request.RelationRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.repository.GroupUserDependentRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public abstract class GroupUserDependentService <T extends GroupUserDependent, GroupRequest extends GroupUserDependentRequest, GroupResponse extends GroupUserDependentResponse> {
    @Autowired
    private GroupUserDependentRepository<T> groupUserDependentRepository;
    @Autowired
    private DependentService dependentService;
    @Autowired
    private RelationService guardService;
    @Autowired
    private DependentGroupService dependentGroupService;
    @Autowired
    private GroupUserDependentMapper<T, GroupRequest, GroupResponse> groupUserDependentMapper;

    public List<GroupResponse> findAll() {
                return this.groupUserDependentRepository
                .findAll()
                .stream()
                .map(this.groupUserDependentMapper::mapGroupUserDependentToGroupUserDependentResponse)
                .toList();
    }

    public GroupResponse findGroupUserDependentById(Long groupUserDependentId) {
        return this.groupUserDependentMapper.mapGroupUserDependentToGroupUserDependentResponse(
            this.groupUserDependentRepository.findById(groupUserDependentId).orElseThrow(
                () -> new EntityNotFoundException(groupUserDependentId, GroupUserDependent.class)));
    }

    @Transactional
    public void deleteGroupUserDependentById(Long groupUserDependentId) {
        this.getDependentsByGroupUserDependentId(groupUserDependentId)
            .forEach((dependent) -> {
                // Means that dependent has only this GroupUserDependent, therefore must be deleted.
                if (this.dependentGroupService.findDependentGroupsByDependentId(dependent.getId()).size() == 1) {
                    dependentService.deleteDependentById(dependent.getId());
                }
            });
        this.groupUserDependentRepository.deleteById(groupUserDependentId);
    }

    public void deleteAllGroupUserDependents() {
        this.dependentService.deleteAllDependents();
        this.groupUserDependentRepository.deleteAll();
    }
    
    @Transactional
    public GroupResponse createGroupUserDependent(GroupRequest groupUserDependentRequest) {
        T groupUserDependent = this.groupUserDependentRepository.save(groupUserDependentMapper.mapGroupUserDependentRequestToGroupUserDependent(groupUserDependentRequest));

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
                // Creates new relation
                RelationRequest newRelation = new RelationRequest();
                newRelation.setDependentId(dependent.getId());
                newRelation.setUserId(groupUserDependentRequest.getUserId());
                newRelation.setUserRole(groupUserDependentRequest.getUserRole());
                this.guardService.createRelation(newRelation);

                // Creates new DependentGroup
                DependentGroup newDependentGroup = new DependentGroup();
                newDependentGroup.setDependentId(dependent.getId());
                newDependentGroup.setGroupId(groupUserDependent.getId());
                this.dependentGroupService.createDependentGroup(newDependentGroup);
                return dependent;
            })
            .toList();

        return this.groupUserDependentMapper.mapGroupUserDependentToGroupUserDependentResponse(groupUserDependent);
    }

    public List<? extends Dependent> getDependentsByGroupUserDependentId(Long groupUserDependentId){
        return dependentGroupService
            .findDependentGroupsByGroupId(groupUserDependentId)
            .stream()
            .map(DependentGroup::getDependentId)
            .map(this.dependentService::findDependentById)
            .toList();
    }
 
    public GroupResponse findByDependentId(Long dependentId) {
        return this.groupUserDependentMapper
            .mapGroupUserDependentToGroupUserDependentResponse(
                this.groupUserDependentRepository.findByDependentId(dependentId).orElseThrow(
                        () -> new EntityNotFoundException(dependentId, Dependent.class)));
    }
}
