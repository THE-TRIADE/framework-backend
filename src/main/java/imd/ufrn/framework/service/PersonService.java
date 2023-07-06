package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.Person;
import imd.ufrn.framework.repository.PersonRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person createPerson(Person newPerson) {
        return this.personRepository.save(newPerson);
    }

    public Person findPersonById(Long personId) {
        return this.personRepository
                   .findById(personId)
                   .orElseThrow(() -> new EntityNotFoundException(personId, Person.class));
    }

    protected void deletePersonById(Long personId) {
        this.personRepository.deleteById(personId);
    }
    
    protected void deleteAllDependents(List<Dependent> dependents) {
        dependents.forEach((dependent) -> this.deletePersonById(dependent.getId()));
    }

    protected void deleteAllUsers(List<User> users) {
        users.forEach((user) -> this.deletePersonById(user.getId()));
    }

}
