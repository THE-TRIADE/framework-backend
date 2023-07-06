package imd.ufrn.framework.model.api.request;

import java.util.List;

import imd.ufrn.framework.model.UserRole;
import jakarta.validation.constraints.NotNull;

public class RelationRequest {
  private List<Integer> daysOfWeek;
  @NotNull
  private UserRole userRole;
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

  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole role) {
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
