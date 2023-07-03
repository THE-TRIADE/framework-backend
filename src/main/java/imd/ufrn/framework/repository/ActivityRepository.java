package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.ActivityAbstract;

public interface ActivityRepository<T extends ActivityAbstract> {
    
    List<T> findAll();
    
    Optional<T> findById(Long id);

    T save(T activity);

    T update(T activity);

    void deleteAll();
    
    void deleteById(Long id);
}
