package imd.ufrn.instancestudentroutine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.instancestudentroutine.repository.mappers.UserInGroupMapper;
import imd.ufrn.instancestudentroutine.model.UserInGroup;

@Repository
public class UserInGroupRepositoryImpl implements UserInGroupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserInGroup> findByUserId(Long userId) {
        String sql = "SELECT * FROM user_in_group WHERE userId = ?";
        return jdbcTemplate.query(sql, new UserInGroupMapper(), userId);
    }

    @Override
    public List<UserInGroup> findByGroupId(Long groupId) {
        String sql = "SELECT * FROM user_in_group WHERE groupId = ?";
        return jdbcTemplate.query(sql, new UserInGroupMapper(), groupId);
    }

    @Override
    public UserInGroup save(UserInGroup userInGroup) {
        String sql = "INSERT INTO user_in_group (`userId`, `groupId`) VALUES (?,?)";
        jdbcTemplate.update(sql, userInGroup.getUserId(), userInGroup.getGroupId());
        return userInGroup;
    }
    
}
