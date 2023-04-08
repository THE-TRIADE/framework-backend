package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Dependent;
import imd.ufrn.familyroutine.repository.DependentRepository;

@Service
public class DependentService{
    @Autowired
    private DependentRepository dependentRepository;

    public List<Dependent> findAll() {
        return this.dependentRepository.findAll();
    }

    public Dependent findDependentById(Long dependentId) {
        return this.dependentRepository.findById(dependentId);
    }

    public void deleteDependentById(Long dependentId) {
        this.dependentRepository.deleteById(dependentId);
    }

    public void deleteAllDependents() {
        this.dependentRepository.deleteAll();
    }

    public Dependent createDependent(Dependent newDependent) {
        return this.dependentRepository.save(newDependent);
    }
}
