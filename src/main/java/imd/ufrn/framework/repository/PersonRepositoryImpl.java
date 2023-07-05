package imd.ufrn.framework.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import imd.ufrn.framework.model.Person;
import imd.ufrn.framework.repository.mappers.PersonMapper;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> findById(Long id) {
        String sql = "SELECT * FROM person WHERE id = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new PersonMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    @Override
    public Person save(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO person (`name`, cpf, birthDate) VALUES (?,?,?)";

        jdbcTemplate.update(connection -> { 
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getName());
            ps.setString(2, person.getCpf());
            ps.setDate(3, person.getBirthDate());
            return ps;
        }, keyHolder);
    
        return this.findById(keyHolder.getKey().longValue()).get();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM person";
        jdbcTemplate.update(sql);
    }
}
