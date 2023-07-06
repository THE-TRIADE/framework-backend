package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.Relation;

public interface RelationRepository {

  List<Relation> findAll();

  Optional<Relation> findById(Long id);

  List<Relation> findByUserId(Long id);

  List<Relation> findByDependentId(Long id);

  Relation save(Relation relation);

  Relation update(Relation relation);

  void deleteById(Long id);

  void deleteAll();
}
