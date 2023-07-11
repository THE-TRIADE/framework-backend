package imd.ufrn.framework.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.framework.model.DependentGroup;
import imd.ufrn.framework.repository.mappers.DependentGroupMapper;

@Repository
public class DependentGroupRepositoryImpl implements DependentGroupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DependentGroup> findByDependentId(Long dependentId) {
        String sql = "SELECT * FROM dependent_group WHERE dependentId = ?";
        return jdbcTemplate.query(sql, new DependentGroupMapper(), dependentId);
    }

    @Override
    public List<DependentGroup> findByGroupId(Long groupId) {
        String sql = "SELECT * FROM dependent_group WHERE groupId = ?";
        return jdbcTemplate.query(sql, new DependentGroupMapper(), groupId);
    }

    @Override
    public DependentGroup save(DependentGroup dependentGroup) {
        String sql = "INSERT INTO dependent_group (`dependentId`, `groupId`) VALUES (?,?)";
        jdbcTemplate.update(sql, dependentGroup.getDependentId(), dependentGroup.getGroupId());
        return dependentGroup;
    }
    
}
