package imd.ufrn.instancestudentroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.instancestudentroutine.model.UserInGroup;
import imd.ufrn.instancestudentroutine.repository.UserInGroupRepository;

@Service
public class UserInGroupService {
    @Autowired
    private UserInGroupRepository userInGroupRepository;

    public List<UserInGroup> findUserInGroupsByUserId(Long userId) { 
        return this.userInGroupRepository.findByUserId(userId);
    }

    public List<UserInGroup> findUserInGroupsByGroupId(Long groupId) { 
        return this.userInGroupRepository.findByGroupId(groupId);
    }

    public UserInGroup createUserInGroup(UserInGroup userinGroup) { 
        return this.userInGroupRepository.save(userinGroup);
    }
}
