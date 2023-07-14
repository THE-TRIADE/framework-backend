package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.UserRole;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getBigDecimal("id").longValue());  
        user.setName(resultSet.getString("name"));
        user.setCpf(resultSet.getString("cpf"));
        user.setBirthDate(resultSet.getDate("birthDate"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        String role = resultSet.getString("role");
        if(role != null) {
            user.setRole(UserRole.valueOf(role));
        }
        return user;
    }
}