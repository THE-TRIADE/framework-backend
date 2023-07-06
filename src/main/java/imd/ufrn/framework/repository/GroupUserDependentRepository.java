package imd.ufrn.framework.repository;


import java.util.List;
import java.util.Optional;

import imd.ufrn.framework.model.Dependent;

import imd.ufrn.framework.model.GroupUserDependent;

public interface GroupUserDependentRepository {

  List<GroupUserDependent> findAll();

  Optional<GroupUserDependent> findById(Long id);

  GroupUserDependent save(GroupUserDependent group);

  void deleteById(Long id);

  void deleteAll();

  Optional<List<Dependent>> findDependentsByGroupUserDependentId(Long groupId);

  Optional<GroupUserDependent> findByDependentId(Long dependentId);

}
