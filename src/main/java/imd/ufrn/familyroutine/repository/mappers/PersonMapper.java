package imd.ufrn.familyroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.familyroutine.model.Person;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getBigDecimal("id").longValue());  
        person.setName(resultSet.getString("name"));
        person.setCpf(resultSet.getString("cpf"));
        person.setBirthDate(resultSet.getDate("birthDate"));
        return person;
    }
}