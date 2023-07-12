package imd.ufrn.instancestudentroutine.repository;

import java.util.List;
import java.util.Optional;

import imd.ufrn.instancestudentroutine.model.Course;

public interface CourseRepository {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    // TODO save(), deleteAll(), deleteById()
}
