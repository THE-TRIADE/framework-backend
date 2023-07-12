package imd.ufrn.instancestudentroutine.model;

import java.sql.Date;

import imd.ufrn.framework.model.Dependent;
import jakarta.validation.constraints.NotNull;

public class DependentStudent extends Dependent{
  @NotNull
  private String race;
  @NotNull
  private Date birthDate;

  public DependentStudent(){}

  public DependentStudent(Long id, String name) {
    super(id, name);
  }

  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }
  
  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

}