package imd.ufrn.instancepetroutine.controller;

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
import imd.ufrn.instancepetroutine.model.api.request.ActivityWithCategoryRequest;
import imd.ufrn.instancepetroutine.model.api.request.FinishActivityWithCategoryRequest;
import imd.ufrn.instancepetroutine.model.api.response.ActivityWithCategoryResponse;
import imd.ufrn.instancepetroutine.service.ActivityWithCategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/activity")
public class ActivityWithCategoryController implements ActivityController<ActivityWithCategoryRequest, FinishActivityWithCategoryRequest, ActivityWithCategoryResponse> {
    @Autowired
    private ActivityWithCategoryService activityService;
    
    @GetMapping
    public List<ActivityWithCategoryResponse> getAllActivities(@RequestParam Optional<Long> dependentId) {
        if(dependentId.isPresent()) {
            return this.activityService.findByDependentId(dependentId.get());
        }
        return this.activityService.findAll();
    }

    @GetMapping("{activityId}")
    public ActivityWithCategoryResponse findActivityById(@PathVariable Long activityId) {
        return this.activityService.findActivityById(activityId);
    }

    @GetMapping("/by-category-id/{categoryId}")
    public List<ActivityWithCategoryResponse> findActivitiesByCategoryId(@PathVariable Long categoryId) {
        return this.activityService.findActivitiesByCategoryId(categoryId);
    }
    @GetMapping("/by-dependent-id/{dependentId}/by-category-id/{categoryId}")
    public List<ActivityWithCategoryResponse> findActivitiesByDependentIdAndCategoryId(@PathVariable Long dependentId, @PathVariable Long categoryId) {
        return this.activityService.findActivitiesByDependentIdAndCategoryId(dependentId,categoryId);
    }
    @PostMapping
    public ActivityWithCategoryResponse createActivity(@RequestBody @Valid ActivityWithCategoryRequest activity) {
        return this.activityService.handleActivityRequest(activity);
    }

    @PatchMapping("{activityId}/finish")
    public ActivityWithCategoryResponse finishActivity(@PathVariable Long activityId, @RequestBody @Valid FinishActivityWithCategoryRequest finishActivityRequest) {
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
