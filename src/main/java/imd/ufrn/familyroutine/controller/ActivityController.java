package imd.ufrn.familyroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.api.ActivityRequest;
import imd.ufrn.familyroutine.service.ActivityService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    
    @GetMapping
    public List<Activity> getAllActivities() {
        return this.activityService.findAll();
    }

    @GetMapping("{activityId}")
    public Activity findActivityById(@PathVariable Long activityId) {
        return this.activityService.findActivityById(activityId);
    }

    @PostMapping
    public Activity createActivity(@RequestBody @Valid ActivityRequest activity) {
        return this.activityService.handleActivityRequest(activity);
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
