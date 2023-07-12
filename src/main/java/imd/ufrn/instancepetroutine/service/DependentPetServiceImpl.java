package imd.ufrn.instancepetroutine.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.instancepetroutine.model.DependentPet;
import imd.ufrn.instancepetroutine.repository.DependentPetRepositoryImpl;

@Service
public class DependentPetServiceImpl implements DependentService<DependentPet>{
    @Autowired
    private DependentPetRepositoryImpl dependentRepository;

    @Override
    public List<DependentPet> findAll() {
        return this.dependentRepository.findAll();
    }

    @Override
    public DependentPet findDependentById(Long dependentId) {
        return this.dependentRepository
            .findById(dependentId)
            .orElseThrow(() -> new EntityNotFoundException(dependentId, DependentPet.class));
    }

    @Override
    public DependentPet createDependent(DependentPet newDependent) {
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
}
