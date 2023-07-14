package imd.ufrn.framework.repository;


import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.GroupUserDependent;

public interface GroupUserDependentRepository <T extends GroupUserDependent> {

  List<T> findAll();

  Optional<T> findById(Long id);

  T save(T group);

  void deleteById(Long id);

  void deleteAll();

  Optional<T> findByDependentId(Long dependentId);

}
