package imd.ufrn.familyroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.familyroutine.model.Activity;

public interface ActivityRepository {
    
    List<Activity> findAll();
    
    Optional<Activity> findById(Long id);

    Activity save(Activity activity);

    void deleteAll();
    
    void deleteById(Long id);
}
