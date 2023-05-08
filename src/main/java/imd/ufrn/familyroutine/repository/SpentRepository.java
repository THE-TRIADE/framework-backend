package imd.ufrn.familyroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.familyroutine.model.Spent;

public interface SpentRepository {

  List<Spent> findAll();

  Optional<Spent> findById(Long id);

  List<Spent> findByGuardianId(Long id);

  List<Spent> findByDependentId(Long id);

  Spent save(Spent spent);

  Spent update(Spent spent);

  void deleteById(Long id);

  void deleteAll();
}
