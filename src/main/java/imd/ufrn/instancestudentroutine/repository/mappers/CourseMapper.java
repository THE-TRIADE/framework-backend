package imd.ufrn.instancestudentroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancestudentroutine.model.Course;

public class CourseMapper implements RowMapper<Course>{

    @Override
    public Course mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setName(resultSet.getString("name"));
        return course;
    }
    
}
