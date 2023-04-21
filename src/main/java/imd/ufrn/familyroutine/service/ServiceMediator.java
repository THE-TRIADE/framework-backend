package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.Dependent;
import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.model.Person;
import imd.ufrn.familyroutine.model.api.ActivityRequest;

@Service
public class ServiceMediator {
    @Lazy
    @Autowired
    private GuardianService guardianService;
    @Lazy
    @Autowired
    private DependentService dependentService;
    @Lazy
    @Autowired
    private PersonService personService;

    @Transactional
    public Dependent createDependent(Dependent newDependent) {
        Person personCreated = this.personService.createPerson(newDependent);
        newDependent.setId(personCreated.getId());
        this.dependentService.createDependent(newDependent);
        return newDependent;
    }

    @Transactional
    public void deleteAllDependents() {
        List<Dependent> dependents = this.dependentService.findAll();
        this.personService.deleteAllDependents(dependents);
    }

    public void deleteDependentById(Long dependentId) {
        this.personService.deletePersonById(dependentId);
    }

    @Transactional
    public Guardian createGuardian(Guardian newGuardian) {
        Person personCreated = this.personService.createPerson(newGuardian);
        newGuardian.setId(personCreated.getId());
        this.guardianService.createGuardian(newGuardian);
        return newGuardian;
    }

    @Transactional
    public void deleteAllGuardians() {
        List<Guardian> guardians = this.guardianService.findAll();
        this.personService.deleteAllGuardians(guardians);
    }

    public void deleteGuardianById(Long guardianId) {
        this.personService.deletePersonById(guardianId);
    }

	public Activity createRecurringActivities(ActivityRequest activityRequest) {
        // TODO
        return null;
	}
}
