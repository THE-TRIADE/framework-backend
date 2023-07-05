package imd.ufrn.instancefamilyroutine.controller;

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
import imd.ufrn.instancefamilyroutine.model.api.request.ActivityStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.request.FinishActivityStandardRequest;
import imd.ufrn.instancefamilyroutine.model.api.response.ActivityStandardResponse;
import imd.ufrn.instancefamilyroutine.service.ActivityStandardService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/activity")
public class ActivityStandardController implements ActivityController<ActivityStandardRequest, FinishActivityStandardRequest, ActivityStandardResponse> {
    @Autowired
    private ActivityStandardService activityService;
    
    @GetMapping
    public List<ActivityStandardResponse> getAllActivities(@RequestParam Optional<Long> dependentId) {
        if(dependentId.isPresent()) {
            return this.activityService.findByDependentId(dependentId.get());
        }
        return this.activityService.findAll();
    }

    @GetMapping("{activityId}")
    public ActivityStandardResponse findActivityById(@PathVariable Long activityId) {
        return this.activityService.findActivityById(activityId);
    }

    @PostMapping
    public ActivityStandardResponse createActivity(@RequestBody @Valid ActivityStandardRequest activity) {
        return this.activityService.handleActivityRequest(activity);
    }

    @PatchMapping("{activityId}/finish")
    public ActivityStandardResponse createActivity(@PathVariable Long activityId, @RequestBody @Valid FinishActivityStandardRequest finishActivityRequest) {
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
