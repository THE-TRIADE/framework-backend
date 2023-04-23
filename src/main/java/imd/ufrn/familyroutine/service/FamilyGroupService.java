package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.FamilyGroup;
import imd.ufrn.familyroutine.repository.FamilyGroupRepository;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;

@Service
public class FamilyGroupService{
    @Autowired
    private FamilyGroupRepository familyGroupRepository;

    public List<FamilyGroup> findAll() {
        return this.familyGroupRepository.findAll();
    }

    public FamilyGroup findFamilyGroupById(Long familyGroupId) {
        return this.familyGroupRepository.findById(familyGroupId).orElseThrow(() -> new EntityNotFoundException(familyGroupId, FamilyGroup.class));
    }

    public void deleteFamilyGroupById(Long familyGroupId) {
        this.familyGroupRepository.deleteById(familyGroupId);
    }

    public void deleteAllFamilyGroups() {
        this.familyGroupRepository.deleteAll();
    }
    
    public FamilyGroup createFamilyGroup(FamilyGroup familyGroup) {
        return this.familyGroupRepository.save(familyGroup);
    }
}
