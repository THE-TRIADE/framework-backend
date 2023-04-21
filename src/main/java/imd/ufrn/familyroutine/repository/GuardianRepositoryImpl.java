package imd.ufrn.familyroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.repository.mappers.GuardianMapper;

@Repository
public class GuardianRepositoryImpl implements GuardianRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Guardian> findAll() {
        String sql = "SELECT * FROM PERSON INNER JOIN GUARDIAN ON PERSON.id = GUARDIAN.personId";
        return jdbcTemplate.query(sql, new GuardianMapper());
    }

    @Override
    public Optional<Guardian> findById(Long id) {
        String sql = "SELECT * FROM PERSON INNER JOIN GUARDIAN ON PERSON.id = GUARDIAN.personId WHERE personId = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new GuardianMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    @Override
    public Guardian save(Guardian guardian) {
        String sql = "INSERT INTO guardian (personId, email, `password`) VALUES (?,?,?)";
        jdbcTemplate.update(sql, guardian.getId(), guardian.getEmail(), guardian.getPassword());
        return guardian;
    }

    @Override
    public Optional<Guardian> findByEmail(String email) {
        String sql = "SELECT * FROM PERSON INNER JOIN GUARDIAN ON PERSON.id = GUARDIAN.personId WHERE email = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new GuardianMapper(), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }
     
}
