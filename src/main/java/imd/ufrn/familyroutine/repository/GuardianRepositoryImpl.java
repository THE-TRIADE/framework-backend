package imd.ufrn.familyroutine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.Guardian;

@Repository
public class GuardianRepositoryImpl implements GuardianRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Guardian> findAll() {
        String sql = "SELECT * FROM guardian";
        return jdbcTemplate.query(sql, 
        (resultSet, rowNumber) -> new Guardian(resultSet.getInt("id"), resultSet.getString("name"), 
            resultSet.getString("cpf"), resultSet.getDate("birthDate"), resultSet.getString("email"), 
            resultSet.getString("password")));
    }

    @Override
    public Guardian findById(Integer id) {
        String sql = "SELECT * FROM guardian WHERE id = " + id;
        return jdbcTemplate.query(sql, 
        (resultSet, rowNumber) -> new Guardian(resultSet.getInt("id"), resultSet.getString("name"), 
            resultSet.getString("cpf"), resultSet.getDate("birthDate"), resultSet.getString("email"), 
            resultSet.getString("password"))).get(0);
    }
     
}
