package imd.ufrn.familyroutine.model.api.response;

import java.sql.Date;

public class SpentResponse {
  private Long id;
  private String name;
  private Integer value;
  private Date paidOn;

  private Long dependentId;
  private String dependentName;

  private Long guardianId;
  private String guardianName;

  private Long activityId;
  private String activityName;

  public SpentResponse() {
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

  public Long getDependentId() {
    return dependentId;
  }

  public void setDependentId(Long dependentId) {
    this.dependentId = dependentId;
  }

  public String getDependentName() {
    return dependentName;
  }

  public void setDependentName(String dependentName) {
    this.dependentName = dependentName;
  }

  public Long getGuardianId() {
    return guardianId;
  }

  public void setGuardianId(Long guardianId) {
    this.guardianId = guardianId;
  }

  public String getGuardianName() {
    return guardianName;
  }

  public void setGuardianName(String guardianName) {
    this.guardianName = guardianName;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

}
