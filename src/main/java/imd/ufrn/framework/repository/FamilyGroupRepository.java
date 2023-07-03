package imd.ufrn.framework.repository;


import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.Dependent;

import imd.ufrn.framework.model.FamilyGroup;

public interface FamilyGroupRepository{

  List<FamilyGroup> findAll();

  Optional<FamilyGroup> findById(Long id);

  FamilyGroup save(FamilyGroup familyGroup);

  void deleteById(Long id);

  void deleteAll();

  Optional<List<Dependent>> findDependentsByFamilyGroupId(Long familyGroupId);

  Optional<FamilyGroup> findByDependentId(Long dependentId);

}
