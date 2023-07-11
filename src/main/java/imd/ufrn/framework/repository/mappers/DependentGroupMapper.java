package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.DependentGroup;

public class DependentGroupMapper implements RowMapper<DependentGroup> {

    @Override
    public DependentGroup mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        DependentGroup dependentGroup = new DependentGroup();
        dependentGroup.setDependentId(resultSet.getBigDecimal("dependentId").longValue());
        dependentGroup.setGroupId(resultSet.getBigDecimal("groupId").longValue());
        return dependentGroup;
    }
    
}