package imd.ufrn.framework.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import imd.ufrn.instancefamilyroutine.model.DependentStandard;
import imd.ufrn.instancepetroutine.model.DependentPet;
import imd.ufrn.instancestudentroutine.model.DependentStudent;
import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DependentStandard.class, name ="DependentStandard"),
    @JsonSubTypes.Type(value = DependentPet.class, name ="DependentPet"),
    @JsonSubTypes.Type(value = DependentStudent.class, name ="DependentStudent"),
})
public abstract class Dependent {
  @Id
  private Long id;
  @NotNull
  private String name;

  public Dependent() {}

  public Dependent(String name) {
    this.name = name;
  }

  public Dependent(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
