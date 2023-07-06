package imd.ufrn.framework.model.api.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class SpentRequest {
  private String name;
  @NotNull
  private Integer value;
  @NotNull
  private LocalDate paidOn;
  @NotNull
  private Long dependentId;
  @NotNull
  private Long userId;
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

  public LocalDate getPaidOn() {
    return paidOn;
  }

  public void setPaidOn(LocalDate paidOn) {
    this.paidOn = paidOn;
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

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }
}
