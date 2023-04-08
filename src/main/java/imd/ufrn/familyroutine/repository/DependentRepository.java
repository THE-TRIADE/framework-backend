package imd.ufrn.familyroutine.repository;

import java.util.List;

import imd.ufrn.familyroutine.model.Dependent;

public interface DependentRepository {

  List<Dependent> findAll();

  Dependent findById(Long id);

  Dependent save(Dependent dependent);

  void deleteById(Long id);

  void deleteAll();

}