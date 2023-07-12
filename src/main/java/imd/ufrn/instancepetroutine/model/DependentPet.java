package imd.ufrn.instancepetroutine.model;

import java.sql.Date;

import imd.ufrn.framework.model.Dependent;
import jakarta.validation.constraints.NotNull;

public class DependentPet extends Dependent{
  @NotNull
  private String race;
  @NotNull
  private Date birthDate;

  public DependentPet(){}

  public DependentPet(Long id, String name) {
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