package imd.ufrn.familyroutine.model;

import java.util.Date;

public class Dependent extends Person {

  public Dependent() {

  }

  public Dependent(Long id, String name, String cpf, Date birthDate) {
    super(id, name, cpf, birthDate);
  }
}
