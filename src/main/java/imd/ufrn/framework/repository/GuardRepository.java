package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.Guard;

public interface GuardRepository {

  List<Guard> findAll();

  Optional<Guard> findById(Long id);

  List<Guard> findByGuardianId(Long id);

  List<Guard> findByDependentId(Long id);

  Guard save(Guard guard);

  Guard update(Guard guard);

  void deleteById(Long id);

  void deleteAll();
}
