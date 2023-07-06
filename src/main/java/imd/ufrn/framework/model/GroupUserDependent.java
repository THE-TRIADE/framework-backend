package imd.ufrn.framework.model;

public class GroupUserDependent {
    private Long id;
    private String name;

    public GroupUserDependent(){}

    public GroupUserDependent(String name){
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