package imd.ufrn.familyroutine.repository;

import java.util.List;

import imd.ufrn.familyroutine.model.Guardian;

public interface GuardianRepository {
    
    List<Guardian> findAll();
    
    Guardian findById(Integer id);

    // TODO Outras operações
}
