package imd.ufrn.familyroutine.model.api.request;

import java.util.List;

import imd.ufrn.familyroutine.model.GuardianRole;
import jakarta.validation.constraints.NotNull;

public class GuardRequest {
  private List<Integer> daysOfWeek;
  @NotNull
  private GuardianRole guardianRole;
  @NotNull
  private Long dependentId;
  @NotNull
  private Long guardianId;

  public GuardRequest() {
  }

  public List<Integer> getDaysOfWeek() {
    return daysOfWeek;
  }

  public void setDaysOfWeek(List<Integer> daysOfWeek) {
    this.daysOfWeek = daysOfWeek;
  }

  public GuardianRole getGuardianRole() {
    return guardianRole;
  }

  public void setGuardianRole(GuardianRole role) {
    this.guardianRole = role;
  }

  public Long getDependentId() {
    return dependentId;
  }

  public void setDependentId(Long dependentId) {
    this.dependentId = dependentId;
  }

  public Long getGuardianId() {
    return guardianId;
  }

  public void setGuardianId(Long guardianId) {
    this.guardianId = guardianId;
  }
}
