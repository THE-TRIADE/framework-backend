package imd.ufrn.framework.model.api.request;

import java.util.List;

import imd.ufrn.framework.model.GuardianRole;
import jakarta.validation.constraints.NotNull;

public class RelationRequest {
  private List<Integer> daysOfWeek;
  @NotNull
  private GuardianRole userRole;
  @NotNull
  private Long dependentId;
  @NotNull
  private Long userId;

  public RelationRequest() {
  }

  public List<Integer> getDaysOfWeek() {
    return daysOfWeek;
  }

  public void setDaysOfWeek(List<Integer> daysOfWeek) {
    this.daysOfWeek = daysOfWeek;
  }

  public GuardianRole getUserRole() {
    return userRole;
  }

  public void setUserRole(GuardianRole role) {
    this.userRole = role;
  }

  public Long getDependentId() {
    return dependentId;
  }

  public void setDependentId(Long dependentId) {
    this.dependentId = dependentId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
