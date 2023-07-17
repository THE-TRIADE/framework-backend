package imd.ufrn.instancestudentroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.instancestudentroutine.model.Course;
import imd.ufrn.instancestudentroutine.model.UserInGroup;

public interface CourseRepository {
    List<Course> findAll();
    Optional<Course> findById(Long id);

    Course save(Course course);

    void deleteAll();

    void deleteById(Long id);
}
