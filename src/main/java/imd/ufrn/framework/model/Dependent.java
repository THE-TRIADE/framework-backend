package imd.ufrn.framework.model;

import java.sql.Date;

public class Dependent extends Person {

  private Long groupId;

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public Dependent() {

  }

  public Dependent(Long id, String name, String cpf, Date birthDate) {
    super(id, name, cpf, birthDate);
  }
}
