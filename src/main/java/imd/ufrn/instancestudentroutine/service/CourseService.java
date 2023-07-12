package imd.ufrn.instancestudentroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.instancestudentroutine.model.Course;
import imd.ufrn.instancestudentroutine.repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course findCourseById(Long courseId) {
        return this.courseRepository
            .findById(courseId)
            .orElseThrow(
                () -> new EntityNotFoundException(courseId, Course.class)
            );
    }
}
