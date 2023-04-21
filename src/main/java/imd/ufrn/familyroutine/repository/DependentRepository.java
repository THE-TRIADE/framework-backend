package imd.ufrn.familyroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.familyroutine.model.Dependent;

public interface DependentRepository {

  List<Dependent> findAll();

  Optional<Dependent> findById(Long id);

  Dependent save(Dependent dependent);

}