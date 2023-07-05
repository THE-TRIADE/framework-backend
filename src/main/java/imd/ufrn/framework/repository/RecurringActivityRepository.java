package imd.ufrn.framework.repository;

import java.util.Optional;

import imd.ufrn.framework.model.RecurringActivity;

public interface RecurringActivityRepository {

    Optional<RecurringActivity> findById(Long id);
    
    RecurringActivity save(RecurringActivity activity);

    void deleteAll();
    
    void deleteById(Long id);
}
