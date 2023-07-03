package imd.ufrn.framework.model;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Guard {
  @Id
  private Long id;
  private List<DayOfWeek> daysOfWeek;
  private GuardianRole guardianRole;
  private Long dependentId;
  private Long guardianId;

  public Guard() {
  }

  public Guard(Long id, List<DayOfWeek> daysOfWeek, GuardianRole guardianRole, Long dependentId,
      Long guardianId) {
    this.id = id;
    this.daysOfWeek = daysOfWeek;
    this.guardianRole = guardianRole;
    this.dependentId = dependentId;
    this.guardianId = guardianId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<DayOfWeek> getDaysOfWeek() {
    return daysOfWeek;
  }

  public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
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
