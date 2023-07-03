package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.Person;
import imd.ufrn.framework.repository.DependentRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public class DependentService{
    @Autowired
    private DependentRepository dependentRepository;
    @Autowired
    private PersonService personService;

    public List<Dependent> findAll() {
        return this.dependentRepository.findAll();
    }

    public Dependent findDependentById(Long dependentId) {
        return this.dependentRepository
            .findById(dependentId)
            .orElseThrow(() -> new EntityNotFoundException(dependentId, Dependent.class));
    }

    @Transactional
    public Dependent createDependentInCascade(Dependent newDependent) {
        Person personCreated = this.personService.createPerson(newDependent);
        newDependent.setId(personCreated.getId());
        this.createDependent(newDependent);
        return newDependent;
    }

    @Transactional
    public void deleteAllDependents() {
        List<Dependent> dependents = this.findAll();
        this.personService.deleteAllDependents(dependents);
    }

    public void deleteDependentById(Long dependentId) {
        this.personService.deletePersonById(dependentId);
    }


    protected Dependent createDependent(Dependent newDependent) {
        return this.dependentRepository.save(newDependent);
    }
}
