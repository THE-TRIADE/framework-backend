package imd.ufrn.instancestudentroutine.model;

import org.springframework.data.annotation.Id;

public class Course {
    @Id
    private Long id;
    private String name;
    
    public Course() {
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
