package imd.ufrn.familyroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.familyroutine.model.Guardian;

public interface GuardianRepository {
    
    List<Guardian> findAll();
    
    Optional<Guardian> findById(Long id);

    Optional<Guardian> findByEmail(String email);

    Guardian save(Guardian guardian);

    void deleteById(Long id);
    
    void deleteAll();

}
