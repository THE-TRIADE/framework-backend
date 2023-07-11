package imd.ufrn.framework.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import imd.ufrn.instancepetroutine.model.DependentStandard;
// import imd.ufrn.instancefamilyroutine.model.DependentStandard;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DependentStandard.class, name ="DependentStandard"),
})
public abstract class Dependent {
  @Id
  private Long id;
  private String name;

  public Dependent() {}

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
