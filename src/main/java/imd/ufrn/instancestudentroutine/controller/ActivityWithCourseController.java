package imd.ufrn.instancestudentroutine.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.controller.ActivityController;
import imd.ufrn.instancestudentroutine.model.api.request.ActivityWithCourseRequest;
import imd.ufrn.instancestudentroutine.model.api.request.FinishActivityWithCourseRequest;
import imd.ufrn.instancestudentroutine.model.api.response.ActivityWithCourseResponse;
import imd.ufrn.instancestudentroutine.service.ActivityWithCourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/activity")
public class ActivityWithCourseController implements ActivityController<ActivityWithCourseRequest, FinishActivityWithCourseRequest, ActivityWithCourseResponse> {
    @Autowired
    private ActivityWithCourseService activityService;
    
    @GetMapping
    public List<ActivityWithCourseResponse> getAllActivities(@RequestParam Optional<Long> dependentId) {
        if(dependentId.isPresent()) {
            return this.activityService.findByDependentId(dependentId.get());
        }
        return this.activityService.findAll();
    }

    @GetMapping("{activityId}")
    public ActivityWithCourseResponse findActivityById(@PathVariable Long activityId) {
        return this.activityService.findActivityById(activityId);
    }

    @GetMapping("/by-course-id/{courseId}")
    public List<ActivityWithCourseResponse> findActivitiesByCourseId(@PathVariable Long courseId) {
        return this.activityService.findActivitiesByCourseId(courseId);
    }
    @GetMapping("/by-dependent-id/{dependentId}/by-course-id/{courseId}")
    public List<ActivityWithCourseResponse> findActivitiesByDependentIdAndCourseId(@PathVariable Long dependentId, @PathVariable Long courseId) {
        return this.activityService.findActivitiesByDependentIdAndCourseId(dependentId,courseId);
    }
    @PostMapping
    public ActivityWithCourseResponse createActivity(@RequestBody @Valid ActivityWithCourseRequest activity) {
        return this.activityService.handleActivityRequest(activity);
    }

    @PatchMapping("{activityId}/finish")
    public ActivityWithCourseResponse finishActivity(@PathVariable Long activityId, @RequestBody @Valid FinishActivityWithCourseRequest finishActivityRequest) {
        return this.activityService.finishActivity(activityId, finishActivityRequest);
    }

    @DeleteMapping
    public void deleteActivity() {
        this.activityService.deleteAllActivities();
    }

    @DeleteMapping("{activityId}")
    public void deleteActivityById(@PathVariable Long activityId) {
        this.activityService.deleteActivityById(activityId);
    } 
}
