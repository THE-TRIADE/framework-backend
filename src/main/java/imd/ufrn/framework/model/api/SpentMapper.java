package imd.ufrn.framework.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.model.Guardian;
import imd.ufrn.framework.model.Spent;
import imd.ufrn.framework.model.api.request.SpentRequest;
import imd.ufrn.framework.model.api.response.ActivityResponse;
import imd.ufrn.framework.model.api.response.SpentResponse;
import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.GuardianService;
import imd.ufrn.instancefamilyroutine.service.ActivityStandardService;

@Component
public class SpentMapper {
  @Autowired
  private GuardianService guardianService;
  @Autowired
  private DependentService dependentService;
  @Autowired
  private ActivityStandardService activityService;

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
