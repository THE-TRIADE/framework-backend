package imd.ufrn.familyroutine.model;


public class FamilyGroup {
    private String name;
    private Long id;

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