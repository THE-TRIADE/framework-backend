package imd.ufrn.framework.model.api.request;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class SpentRequest {
  private String name;
  @NotNull
  private Integer value;
  @NotNull
  private Date paidOn;
  @NotNull
  private Long dependentId;
  @NotNull
  private Long guardianId;
  private Long activityId;

  public SpentRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public Date getPaidOn() {
    return paidOn;
  }

  public void setPaidOn(Date paidOn) {
    this.paidOn = paidOn;
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

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }
}
