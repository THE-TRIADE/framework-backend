package imd.ufrn.instancestudentroutine.repository;

import java.util.List;

import imd.ufrn.instancestudentroutine.model.UserInGroup;

public interface UserInGroupRepository {
  List<UserInGroup> findByGroupId(Long groupId);

  List<UserInGroup> findByUserId(Long userId);

  UserInGroup save(UserInGroup userInGroup);
}
