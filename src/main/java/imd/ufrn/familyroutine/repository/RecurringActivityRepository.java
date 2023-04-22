package imd.ufrn.familyroutine.repository;

import java.util.Optional;

import imd.ufrn.familyroutine.model.RecurringActivity;

public interface RecurringActivityRepository {

    Optional<RecurringActivity> findById(Long id);
    
    RecurringActivity save(RecurringActivity activity);

    void deleteAll();
    
    void deleteById(Long id);
}
