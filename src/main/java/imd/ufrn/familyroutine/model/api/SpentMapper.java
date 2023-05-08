package imd.ufrn.familyroutine.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import imd.ufrn.familyroutine.model.*;
import imd.ufrn.familyroutine.model.api.request.SpentRequest;
import imd.ufrn.familyroutine.model.api.response.ActivityResponse;
import imd.ufrn.familyroutine.model.api.response.SpentResponse;
import imd.ufrn.familyroutine.service.DependentService;
import imd.ufrn.familyroutine.service.GuardianService;
import imd.ufrn.familyroutine.service.ActivityService;

@Component
public class SpentMapper {
  @Autowired
  private GuardianService guardianService;
  @Autowired
  private DependentService dependentService;
  @Autowired
  private ActivityService activityService;

  public Spent mapSpentRequestToSpent(SpentRequest spentRequest) {
    Spent spent = new Spent();

    spent.setName(spentRequest.getName());
    spent.setValue(spentRequest.getValue());
    spent.setPaidOn(spentRequest.getPaidOn());
    spent.setDependentId(spentRequest.getDependentId());
    spent.setGuardianId(spentRequest.getGuardianId());

    if (spentRequest.getActivityId() != null) {
      spent.setActivityId(spentRequest.getActivityId());
    }
    return spent;
  }

  public SpentResponse mapSpentToSpentResponse(Spent spent) {
    SpentResponse spentResponse = new SpentResponse();

    spentResponse.setId(spent.getId());
    spentResponse.setName(spent.getName());
    spentResponse.setValue(spent.getValue());
    spentResponse.setPaidOn(spent.getPaidOn());

    spentResponse.setDependentId(spent.getDependentId());
    Dependent dependent = this.dependentService.findDependentById(spent.getDependentId());
    spentResponse.setDependentName(dependent.getName());

    spentResponse.setGuardianId(spent.getGuardianId());
    Guardian guardian = this.guardianService.findGuardianById(spent.getGuardianId());
    spentResponse.setGuardianName(guardian.getName());

    if (spent.getActivityId() != null) {
      spentResponse.setActivityId(spent.getActivityId());
      ActivityResponse activity = this.activityService.findActivityById(spent.getActivityId());
      spentResponse.setActivityName(activity.getName());
    }

    return spentResponse;
  }
}
