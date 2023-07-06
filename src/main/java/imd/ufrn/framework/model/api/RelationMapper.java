package imd.ufrn.framework.model.api;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.*;
import imd.ufrn.framework.model.api.request.RelationRequest;
import imd.ufrn.framework.model.api.response.RelationResponse;
import imd.ufrn.framework.service.DependentService;
import imd.ufrn.framework.service.UserService;
import imd.ufrn.framework.service.exception.InvalidDayException;

@Component
public class RelationMapper {
  @Lazy
  @Autowired
  private UserService userService;
  @Autowired
  private DependentService dependentService;

  public Relation mapRelationRequestToRelation(RelationRequest relationRequest) {
    Relation relation = new Relation();

    if (relationRequest.getDaysOfWeek() != null) {
      List<DayOfWeek> days = relationRequest.getDaysOfWeek()
          .stream()
          .map(intValue -> {
            try {
              return DayOfWeek.of(intValue);
            } catch (Exception e) {
              throw new InvalidDayException();
            }
          })
          .toList();

      relation.setDaysOfWeek(days);
    }

    relation.setUserRole(relationRequest.getUserRole());
    relation.setDependentId(relationRequest.getDependentId());
    relation.setUserId(relationRequest.getUserId());

    return relation;
  }

  public RelationResponse mapRelationToRelationResponse(Relation relation) {
    RelationResponse relationResponse = new RelationResponse();

    if (relation.getDaysOfWeek() != null) {
      List<Integer> daysInt = relation.getDaysOfWeek()
          .stream()
          .map(day -> day.getValue())
          .toList();

      relationResponse.setDaysOfWeek(daysInt);
    }

    relationResponse.setId(relation.getId());
    relationResponse.setUserRole(relation.getUserRole());

    relationResponse.setDependentId(relation.getDependentId());
    Dependent dependent = this.dependentService.findDependentById(relation.getDependentId());
    relationResponse.setDependentName(dependent.getName());

    relationResponse.setUserId(relation.getUserId());
    User user = this.userService.findUserById(relation.getUserId());
    relationResponse.setUserName(user.getName());
    relationResponse.setUserEmail(user.getEmail());

    return relationResponse;
  }
}
