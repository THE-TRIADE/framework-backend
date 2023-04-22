package imd.ufrn.familyroutine.repository;

import java.util.Optional;

import imd.ufrn.familyroutine.model.Person;

public interface PersonRepository {
    
    Optional<Person> findById(Long id);

    Person save(Person person);

    void deleteById(Long id);
    
    void deleteAll();
}