package imd.ufrn.familyroutine.repository;


import java.util.List;
import java.util.Optional;

import imd.ufrn.familyroutine.model.FamilyGroup;

public interface FamilyGroupRepository{

  List<FamilyGroup> findAll();

  Optional<FamilyGroup> findById(Long id);

  FamilyGroup save(FamilyGroup familyGroup);

  void deleteById(Long id);

  void deleteAll();

}
