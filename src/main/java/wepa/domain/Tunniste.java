package wepa.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tunniste extends AbstractPersistable<Long>{
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
