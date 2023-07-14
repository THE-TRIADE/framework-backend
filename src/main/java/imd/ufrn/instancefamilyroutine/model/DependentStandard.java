package imd.ufrn.instancefamilyroutine.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import imd.ufrn.framework.model.Dependent;

public class DependentStandard extends Dependent{

  private String cpf;
  // @JsonFormat(pattern="yyyy-MM-dd")
  private Date birthDate;

  public DependentStandard(){}

  public DependentStandard(Long id, String name, String cpf, Date birthDate) {
    super(id, name);
    this.cpf = cpf;
    this.birthDate = birthDate;
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