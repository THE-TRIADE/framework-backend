package imd.ufrn.framework.model.api.response;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import imd.ufrn.framework.model.UserRole;

public class UserResponse {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;
    private Set<UserRole> roles = new HashSet<>();
    private List<GroupUserDependentResponse> groups;
    private List<RelationToUserResponse> relations;

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

    public List<GroupUserDependentResponse> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupUserDependentResponse> groups) {
        this.groups = groups;
    }

    public List<RelationToUserResponse> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationToUserResponse> guards) {
        this.relations = guards;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
