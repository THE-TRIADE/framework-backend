package imd.ufrn.framework.model.api.response;

import java.time.LocalDate;
import java.util.List;

public class UserResponse {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;
    private List<FamilyGroupResponse> familyGroups;
    private List<GuardToUserResponse> guards;

    public UserResponse() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<FamilyGroupResponse> getFamilyGroups() {
        return familyGroups;
    }

    public void setFamilyGroups(List<FamilyGroupResponse> familyGroups) {
        this.familyGroups = familyGroups;
    }

    public List<GuardToUserResponse> getGuards() {
        return guards;
    }

    public void setGuards(List<GuardToUserResponse> guards) {
        this.guards = guards;
    }

}
