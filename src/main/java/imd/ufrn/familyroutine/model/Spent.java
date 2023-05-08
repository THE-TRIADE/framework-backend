package imd.ufrn.familyroutine.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

public class Spent {
  @Id
  private Long id;
  private String name;
  private Integer value;
  private Date paidOn;

  private Long activityId;
  private Long dependentId;
  private Long guardianId;

  public Spent() {
  }

  public Spent(Long id, String name, Integer value, Date paidOn, Long activityId, Long dependentId, Long guardianId) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.paidOn = paidOn;
    this.activityId = activityId;
    this.dependentId = dependentId;
    this.guardianId = guardianId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
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
