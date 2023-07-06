package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.GroupUserDependent;
import imd.ufrn.framework.model.api.GroupUserDependentMapper;
import imd.ufrn.framework.model.api.request.GroupUserDependentRequest;
import imd.ufrn.framework.model.api.request.RelationRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.repository.GroupUserDependentRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.instancefamilyroutine.model.DependentStandard;

@Service
public class GroupUserDependentService {
    @Autowired
    private GroupUserDependentRepository groupUserDependentRepository;
    @Autowired
    private DependentService<DependentStandard> dependentService;
    @Autowired
    private RelationService guardService;
    @Autowired
    private GroupUserDependentMapper groupUserDependentMapper;

    public List<GroupUserDependentResponse> findAll() {
                return this.groupUserDependentRepository
                .findAll()
                .stream()
                .map(this.groupUserDependentMapper::mapGroupUserDependentToGroupUserDependentResponse)
                .toList();
    }

    public GroupUserDependentResponse findGroupUserDependentById(Long groupUserDependentId) {
        return this.groupUserDependentMapper.mapGroupUserDependentToGroupUserDependentResponse(
            this.groupUserDependentRepository.findById(groupUserDependentId).orElseThrow(
                () -> new EntityNotFoundException(groupUserDependentId, GroupUserDependent.class)));
    }

    public void deleteGroupUserDependentById(Long groupUserDependentId) {
        this.getGroupUserDependentDependentsByGroupUserDependentId(groupUserDependentId).forEach(
            (x) -> {dependentService.deleteDependentById(x.getId());} );
        this.groupUserDependentRepository.deleteById(groupUserDependentId);
    }

    public void deleteAllGroupUserDependents() {
        this.dependentService.deleteAllDependents();
        this.groupUserDependentRepository.deleteAll();
    }
    
    @Transactional
    public GroupUserDependentResponse createGroupUserDependent(GroupUserDependentRequest groupUserDependentRequest) {
        GroupUserDependent groupUserDependent = this.groupUserDependentRepository.save(groupUserDependentMapper.mapGroupUserDependentRequestToGroupUserDependent(groupUserDependentRequest));
        
        if(groupUserDependentRequest.getDependents() == null) {
            return this.groupUserDependentMapper.mapGroupUserDependentToGroupUserDependentResponse(groupUserDependent);
        }
        for (Dependent dependent : groupUserDependentRequest.getDependents()) {
            DependentStandard dependentStandard = (DependentStandard)dependent;
            dependentStandard.setGroupId(groupUserDependent.getId());
            dependentStandard.setId(dependentService.createDependent(dependentStandard).getId());
        }

        for (Dependent dependent : groupUserDependentRequest.getDependents()) {
            RelationRequest newRelation = new RelationRequest();
            newRelation.setDependentId(dependent.getId());
            newRelation.setUserId(groupUserDependentRequest.getUserId());
            newRelation.setUserRole(groupUserDependentRequest.getUserRole());
            this.guardService.createRelation(newRelation);
        }

        return this.groupUserDependentMapper.mapGroupUserDependentToGroupUserDependentResponse(groupUserDependent);
    }

    public List<DependentStandard> getGroupUserDependentDependentsByGroupUserDependentId(Long groupUserDependentId){
        return dependentService.findDependentsByGroupUserDependentId(groupUserDependentId);
    }

    public GroupUserDependentResponse findByDependentId(Long dependentId) {
        return this.groupUserDependentMapper
            .mapGroupUserDependentToGroupUserDependentResponse(
                this.groupUserDependentRepository.findByDependentId(dependentId).orElseThrow(
                        () -> new EntityNotFoundException(dependentId, Dependent.class)));
    }
}
