package imd.ufrn.instancestudentroutine.model;

import imd.ufrn.framework.model.Dependent;
import jakarta.validation.constraints.NotNull;

public class DependentStudent extends Dependent{
  // @NotNull
  private String cpf;
  @NotNull String registrationNumber;


  public DependentStudent(){}

  public DependentStudent(String name, String cpf, String registrationNumber) {
    super(name);
    this.cpf = cpf;
    this.registrationNumber = registrationNumber;
  }

  public DependentStudent(Long id, String name, String cpf, String registrationNumber) {
    super(id, name);
    this.cpf = cpf;
    this.registrationNumber = registrationNumber;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

}