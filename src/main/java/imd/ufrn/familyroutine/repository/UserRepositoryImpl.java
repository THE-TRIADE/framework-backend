package imd.ufrn.familyroutine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import imd.ufrn.familyroutine.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM `user`";

        return jdbcTemplate.query(sql, 
        (resultSet, rowNumber) -> new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email")));
    }

    @Override
    public User finById(Integer id) {
        String sql = "SELECT * FROM `user` WHERE id = " + id;

        return jdbcTemplate.query(sql, 
        (resultSet, rowNumber) -> new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"))).get(0);
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute("DELETE * FROM user");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
