package imd.ufrn.instancepetroutine.model;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private Long id;
    private String name;
    
    public Category() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
