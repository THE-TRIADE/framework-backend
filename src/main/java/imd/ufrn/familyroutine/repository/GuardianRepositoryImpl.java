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
        return jdbcTemplate.query(sql, new GuardianMapper());
    }

    @Override
    public Guardian findById(Long id) {
        String sql = "SELECT * FROM guardian WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new GuardianMapper());
    }

    @Override
    public Guardian save(Guardian guardian) {
        String sql = "INSERT INTO guardian (id, `name`, cpf, birthDate, email, `password`) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, guardian.getId(), guardian.getName(),  guardian.getCpf(), 
                    guardian.getBirthDate(), guardian.getEmail(),  guardian.getPassword());
        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE * FROM guardian WHERE id = " + id;
        jdbcTemplate.update(sql, id > 0);
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM guardian";
        jdbcTemplate.update(sql, true);
    }
     

    
}
