package imd.ufrn.instancestudentroutine.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import imd.ufrn.framework.model.Dependent;
import jakarta.validation.constraints.NotNull;

public class DependentStudent extends Dependent{
  // @NotNull
  // private String raced;
  // @JsonFormat(pattern="yyyy-MM-dd")
  @NotNull
  private Date birthDate;

  public DependentStudent(){}

  public DependentStudent(String name, Date birthDate) {
    super(name);
    this.birthDate = birthDate;
  }
  public DependentStudent(Long id, String name, Date birthDate) {
    super(id, name);
    this.birthDate = birthDate;
  }

  // public String getRace() {
  //   return raced;
  // }

  // public void setRace(String race) {
  //   this.raced = race;
  // }
  
  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

}