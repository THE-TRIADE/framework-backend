package imd.ufrn.framework.model;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class User implements UserDetails {
    
    @Id
    private Long id;
    @NotEmpty
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String cpf;
    @NotNull
    private Date birthDate;
    private UserRole role;

    public User() {

    }

    public User(Long id, String email, String password, String name, String cpf, Date birthDate, UserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.role = role;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
