package imd.ufrn.familyroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.familyroutine.model.Guardian;

public class GuardianMapper implements RowMapper<Guardian> {

    @Override
    public Guardian mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Guardian guardian = new Guardian();
        guardian.setId(resultSet.getBigDecimal("id").longValue());  
        guardian.setName(resultSet.getString("name"));
        guardian.setCpf(resultSet.getString("cpf"));
        guardian.setBirthDate(resultSet.getDate("birthDate"));
        guardian.setEmail(resultSet.getString("email"));
        guardian.setPassword(resultSet.getString("password"));
        return guardian;
    }
}