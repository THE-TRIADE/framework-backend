package imd.ufrn.instancestudentroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.instancestudentroutine.model.Course;
import imd.ufrn.instancestudentroutine.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired 
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.findAllCourses();
    }

    @GetMapping("{courseId}")
    public Course findCourseById(@PathVariable Long courseId) {
        return this.courseService.findCourseById(courseId);
    }
}
