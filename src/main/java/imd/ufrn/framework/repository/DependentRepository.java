package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.Dependent;

public interface DependentRepository <T extends Dependent> {

  List<T> findAll();

  Optional<T> findById(Long id);

  T save(T dependent);

  void deleteAll();

  void deleteById(Long id);
}