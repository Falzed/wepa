package wepa.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.Temporal;

@Entity
public class Kommentti extends AbstractPersistable<Long> {
    
    @ManyToOne
    @JoinColumn
    private Kuva kuva;
    private Kayttaja kayttaja;
    private Timestamp aika; 
    private String sisalto;

    
    public Kuva getKuva() {
        return kuva;
    }

    public void setKuva(Kuva kuva) {
        this.kuva = kuva;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }
    
    public String getKayttajanNimi() {
        return kayttaja.getUsername();
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public Timestamp getAika() {
        return aika;
    }

    public void setAika(Timestamp aika) {
        this.aika = aika;
    }

    public String getSisalto() {
        return sisalto;
    }

    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }
    
}