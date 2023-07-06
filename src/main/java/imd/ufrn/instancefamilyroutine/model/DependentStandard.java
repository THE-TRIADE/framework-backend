package imd.ufrn.instancefamilyroutine.model;

import java.sql.Date;

import imd.ufrn.framework.model.Dependent;

public class DependentStandard extends Dependent{

  private String cpf;
  private Date birthDate;

  public DependentStandard(){}

  public DependentStandard(Long id, Long groupId, String name) {
    super(id, groupId, name);
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

}