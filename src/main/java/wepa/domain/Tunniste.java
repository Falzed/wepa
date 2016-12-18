package wepa.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tunniste extends AbstractPersistable<Long>{
    @NotBlank(message = "Tunnisteella täytyy olla nimi")
    @Length(min = 1, max = 50, message = "Nimen pituus 1-50 merkkiä")
    private String nimi;
    
    @ManyToMany
    List<Kuva> kuvat;
    
    public String getNimi() {
        return nimi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
}
