package imd.ufrn.familyroutine.model;

import java.sql.Date;

public class Dependent extends Person {

  private Long familyGroupId;

  public Long getFamilyGroupId() {
    return familyGroupId;
  }

  public void setFamilyGroupId(Long familyGroupId) {
    this.familyGroupId = familyGroupId;
  }

  public Dependent() {

  }

  public Dependent(Long id, String name, String cpf, Date birthDate) {
    super(id, name, cpf, birthDate);
  }
}
