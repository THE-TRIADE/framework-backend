package imd.ufrn.framework.model;

public class FamilyGroup {
    private Long id;
    private String name;

    public FamilyGroup(){}

    public FamilyGroup(String name){
        this.name = name;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}