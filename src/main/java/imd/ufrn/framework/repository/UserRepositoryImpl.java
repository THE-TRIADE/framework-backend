package imd.ufrn.framework.repository;

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

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.repository.mappers.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM `user`";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM `user` WHERE id = ?";       
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new UserMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }

    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO `user` (`name`, cpf, birthDate, email, `password`) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(connection -> { 
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getCpf());
            ps.setDate(3, user.getBirthDate());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());

            return ps;
        }, keyHolder);
    
        return this.findById(keyHolder.getKey().longValue()).get();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM `user` WHERE email = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new UserMapper(), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } 
    }
     
    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM `user` WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM `user`";
        jdbcTemplate.update(sql);
    }
}
