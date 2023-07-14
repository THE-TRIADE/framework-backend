package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.User;

public interface UserRepository {
    
    List<User> findAll();
    
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findByRole(String role);

    User save(User user);

    void deleteById(Long id);
    
    void deleteAll();
}
