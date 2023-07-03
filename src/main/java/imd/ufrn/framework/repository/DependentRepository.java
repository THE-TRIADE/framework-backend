package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.Dependent;

public interface DependentRepository {

  List<Dependent> findAll();

  Optional<Dependent> findById(Long id);

  Dependent save(Dependent dependent);

}