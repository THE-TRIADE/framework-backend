package imd.ufrn.instancestudentroutine.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.instancestudentroutine.model.DependentStudent;
import imd.ufrn.instancestudentroutine.repository.DependentStudentRepositoryImpl;

@Service
public class DependentServiceImpl implements DependentService<DependentStudent>{
    @Autowired
    private DependentStudentRepositoryImpl dependentRepository;

    @Override
    public List<DependentStudent> findAll() {
        return this.dependentRepository.findAll();
    }

    @Override
    public DependentStudent findDependentById(Long dependentId) {
        return this.dependentRepository
            .findById(dependentId)
            .orElseThrow(() -> new EntityNotFoundException(dependentId, DependentStudent.class));
    }

    @Override
    public DependentStudent createDependent(DependentStudent newDependent) {
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
