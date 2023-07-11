package imd.ufrn.instancepetroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancepetroutine.model.Category;

public class CategoryMapper implements RowMapper<Category>{

    @Override
    public Category mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
    
}
