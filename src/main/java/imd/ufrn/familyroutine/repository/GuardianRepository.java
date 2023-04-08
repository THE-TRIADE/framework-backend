package imd.ufrn.familyroutine.repository;

import java.util.List;

import imd.ufrn.familyroutine.model.Guardian;

public interface GuardianRepository {
    
    List<Guardian> findAll();
    
    Guardian findById(Long id);

    Guardian save(Guardian guardian);

    void deleteById(Long id);
    
    void deleteAll();

}
