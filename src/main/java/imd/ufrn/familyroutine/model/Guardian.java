package imd.ufrn.familyroutine.model;

import java.util.Date;

public class Guardian extends Person {
    private String email;
    private String password;

    public Guardian() {

    }

    public Guardian(Integer id, String name, String cpf, Date birthDate, String email, String password) {
        super(id, name, cpf, birthDate);
        this.email = email;
        this.password = password;
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
}
