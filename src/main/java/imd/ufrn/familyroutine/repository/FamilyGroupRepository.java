package imd.ufrn.familyroutine.repository;


import java.util.List;

import imd.ufrn.familyroutine.model.FamilyGroup;

public interface FamilyGroupRepository{

  List<FamilyGroup> findAll();

  FamilyGroup findById(Long id);

  FamilyGroup save(FamilyGroup familyGroup);

  void deleteById(Long id);

  void deleteAll();

}
