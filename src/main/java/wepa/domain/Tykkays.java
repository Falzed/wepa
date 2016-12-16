
package wepa.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tykkays extends AbstractPersistable<Long> {
    @ManyToOne
    @JoinColumn
    private Kayttaja kayttaja;
    @ManyToOne
    @JoinColumn
    private Kuva kuva;

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public Kuva getKuva() {
        return kuva;
    }

    public void setKuva(Kuva kuva) {
        this.kuva = kuva;
    }
  
    
}
