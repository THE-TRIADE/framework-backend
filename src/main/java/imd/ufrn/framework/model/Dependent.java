package imd.ufrn.framework.model;

import org.springframework.data.annotation.Id;

public abstract class Dependent {
  @Id
  private Long id;
  private Long groupId;
  private String name;

  public Dependent() {}

  public Dependent(Long id, Long groupId, String name) {
    this.id = id;
    this.groupId = groupId;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
