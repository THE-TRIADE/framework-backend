package imd.ufrn.instancefamilyroutine.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.instancefamilyroutine.model.DependentStandard;
import imd.ufrn.instancefamilyroutine.repository.DependentRepositoryImpl;

@Service
public class DependentServiceImpl implements DependentService<DependentStandard>{
    @Autowired
    private DependentRepositoryImpl dependentRepository;

    @Override
    public List<DependentStandard> findAll() {
        return this.dependentRepository.findAll();
    }

    @Override
    public DependentStandard findDependentById(Long dependentId) {
        return this.dependentRepository
            .findById(dependentId)
            .orElseThrow(() -> new EntityNotFoundException(dependentId, DependentStandard.class));
    }

    @Override
    public DependentStandard createDependent(DependentStandard newDependent) {
        return this.dependentRepository.save(newDependent);
    }

    @Override
    public void deleteAllDependents() {
        this.dependentRepository.deleteAll();
    }

    @Override
    public void deleteDependentById(Long dependentId) {
        this.dependentRepository.deleteById(dependentId);
    }

    @Override
    public List<DependentStandard> findDependentsByGroupUserDependentId(Long groupId) {
        return dependentRepository.findDependentsByGroupUserDependentId(groupId).orElse(new ArrayList<>());
    }

}
