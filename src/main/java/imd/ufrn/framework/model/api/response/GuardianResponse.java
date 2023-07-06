package imd.ufrn.framework.model.api.response;

import java.time.LocalDate;
import java.util.List;

public class GuardianResponse {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;
    private List<GroupUserDependentResponse> familyGroups;
    private List<GuardToGuardianResponse> guards;

    public GuardianResponse() {
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

    public List<GroupUserDependentResponse> getFamilyGroups() {
        return familyGroups;
    }

    public void setFamilyGroups(List<GroupUserDependentResponse> familyGroups) {
        this.familyGroups = familyGroups;
    }

    public List<GuardToGuardianResponse> getGuards() {
        return guards;
    }

    public void setGuards(List<GuardToGuardianResponse> guards) {
        this.guards = guards;
    }

}
