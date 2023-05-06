package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.familyroutine.model.Dependent;
import imd.ufrn.familyroutine.model.FamilyGroup;
import imd.ufrn.familyroutine.model.api.FamilyGroupMapper;
import imd.ufrn.familyroutine.model.api.request.FamilyGroupRequest;
import imd.ufrn.familyroutine.model.api.response.FamilyGroupResponse;
import imd.ufrn.familyroutine.repository.FamilyGroupRepository;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;

@Service
public class FamilyGroupService{
    @Autowired
    private FamilyGroupRepository familyGroupRepository;
    @Autowired
    private DependentService dependentService;
    @Autowired
    private FamilyGroupMapper familyGroupMapper;

    public List<FamilyGroupResponse> findAll() {
                return this.familyGroupRepository
                .findAll()
                .stream()
                .map(this.familyGroupMapper::mapFamilyGroupToFamilyGroupResponse)
                .toList();
    }

    public FamilyGroupResponse findFamilyGroupById(Long familyGroupId) {
        return this.familyGroupMapper.mapFamilyGroupToFamilyGroupResponse(
            this.familyGroupRepository.findById(familyGroupId).orElseThrow(
                () -> new EntityNotFoundException(familyGroupId, FamilyGroup.class)));
    }

    public void deleteFamilyGroupById(Long familyGroupId) {
        this.getFamilyGroupDependentsById(familyGroupId).forEach(
            (x) -> {dependentService.deleteDependentById(x.getId());} );
        this.familyGroupRepository.deleteById(familyGroupId);
    }

    public void deleteAllFamilyGroups() {
        this.dependentService.deleteAllDependents();
        this.familyGroupRepository.deleteAll();
    }
    
    @Transactional
    public FamilyGroupResponse createFamilyGroup(FamilyGroupRequest familyGroupRequest) {
        FamilyGroup familyGroup = this.familyGroupRepository.save(familyGroupMapper.mapFamilyGroupRequestToFamilyGroup(familyGroupRequest));
        
        if(familyGroupRequest.getDependents() == null) return this.familyGroupMapper.mapFamilyGroupToFamilyGroupResponse(familyGroup);
        for (Dependent dependent : familyGroupRequest.getDependents()) {
            dependent.setFamilyGroupId(familyGroup.getId());
            dependentService.createDependentInCascade(dependent);
        }

        return this.familyGroupMapper.mapFamilyGroupToFamilyGroupResponse(familyGroup);
    }

    public List<Dependent> getFamilyGroupDependentsById(Long familyGroupId){
        return familyGroupRepository.getDependents(familyGroupId);
    }
}
