package imd.ufrn.framework.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql = "INSERT INTO `user` (id, `name`, cpf, birthDate, email, `password`) VALUES (?,?,?,?,?,?)";
        user.setId(getNextId());
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getCpf(), user.getBirthDate(), user.getEmail(), user.getPassword());
        return user;
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

    private Long getNextId() {
        Long maxUserId = jdbcTemplate
            .query("SELECT id FROM `user`", (rs, rn) ->  rs.getBigDecimal("id").longValue())
            .stream()
            .reduce(Long::max)
            .get();

        Long maxDependentId = jdbcTemplate
            .query("SELECT id FROM `dependent`", (rs, rn) ->  rs.getBigDecimal("id").longValue())
            .stream()
            .reduce(Long::max)
            .get();

        if (maxUserId == null && maxDependentId == null) {
            return 1L;
        } else if (maxUserId == null) {
            return maxDependentId + 1;
        } else if (maxDependentId == null) {
            return maxUserId + 1;
        } else {
            return Math.max(maxUserId, maxDependentId) + 1;
        }
    }
}
