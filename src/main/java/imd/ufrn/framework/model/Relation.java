package imd.ufrn.framework.model;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Relation {
  @Id
  private Long id;
  private List<DayOfWeek> daysOfWeek;
  private GuardianRole userRole;
  private Long dependentId;
  private Long userId;

  public Relation() {
  }

  public Relation(Long id, List<DayOfWeek> daysOfWeek, GuardianRole userRole, Long dependentId,
      Long userId) {
    this.id = id;
    this.daysOfWeek = daysOfWeek;
    this.userRole = userRole;
    this.dependentId = dependentId;
    this.userId = userId;
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
