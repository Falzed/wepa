package wepa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class Kayttaja extends AbstractPersistable<Long> {

    @NotBlank(message = "Käyttäjätunnus ei voi olla tyhjä")
    @Column(unique = true)
    @Length(min = 4, max = 20, message = "Käyttäjätunnuksen pituus 4-20 merkkiä")
    private String username;
    
    @NotBlank(message = "Salasana ei voi olla tyhjä")
//    @Length(min = 4, max = 2000, message = "Salasanan pituus 4-20 merkkiä")
    private String password;
    
    private String salt;
    
    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    
}
