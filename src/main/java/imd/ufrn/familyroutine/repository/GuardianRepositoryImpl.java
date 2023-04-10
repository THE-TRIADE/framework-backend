package imd.ufrn.familyroutine.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.repository.mappers.GuardianMapper;

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
    public Optional<Guardian> findById(Long id) {
        String sql = "SELECT * FROM guardian WHERE id = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new GuardianMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    @Override
    public Guardian save(Guardian guardian) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO guardian (`name`, cpf, birthDate, email, `password`) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(connection -> { 
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, guardian.getName());
            ps.setString(2, guardian.getCpf());
            ps.setDate(3, (Date) guardian.getBirthDate());
            ps.setString(4, guardian.getEmail());
            ps.setString(5, guardian.getPassword());
            return ps;
        }, keyHolder);
    
        return this.findById(keyHolder.getKey().longValue()).get();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM guardian WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM guardian";
        jdbcTemplate.update(sql);
    }

    @Override
    public Optional<Guardian> findByEmail(String email) {
        String sql = "SELECT * FROM guardian WHERE email = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new GuardianMapper(), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }
     

    
}
