package imd.ufrn.framework.repository;

import java.util.Optional;

import imd.ufrn.framework.model.Person;

public interface PersonRepository {
    
    Optional<Person> findById(Long id);

    Person save(Person person);

    void deleteById(Long id);
    
    void deleteAll();
}