package imd.ufrn.framework.model.api.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class UserRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String cpf;
    @NotNull
    private LocalDate birthDate;

    private String role;

    public UserRequest() {
    }

    public UserRequest(@NotNull String email, @NotNull String password, @NotNull String name, @NotNull String cpf,
            @NotNull LocalDate birthDate, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
