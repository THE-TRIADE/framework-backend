package imd.ufrn.framework.repository;

import java.util.List;

import imd.ufrn.framework.model.DependentGroup;

public interface DependentGroupRepository {
  List<DependentGroup> findByGroupId(Long groupId);

  List<DependentGroup> findByDependentId(Long dependentId);

  DependentGroup save(DependentGroup dependentGroup);
}
