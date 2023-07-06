package imd.ufrn.framework.model.api.response;

import java.util.List;

import imd.ufrn.framework.model.UserRole;

public class RelationResponse {
  private Long id;
  private List<Integer> daysOfWeek;
  private UserRole userRole;

  private Long dependentId;
  private String dependentName;

  private Long userId;
  private String userName;
  private String userEmail;

  public RelationResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getDependentName() {
    return dependentName;
  }

  public void setDependentName(String dependentName) {
    this.dependentName = dependentName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
