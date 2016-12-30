package wepa.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Kommentti extends AbstractPersistable<Long> {
    
    @ManyToOne
    @JoinColumn
    private Kuva kuva;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Kayttaja kayttaja;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date aika; 
    
    @NotBlank(message = "Kommentti ei voi olla tyhjä")
    @Length(min = 1, max = 300, message = "Kommentin pituus 1-300 merkkiä")
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

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }
    
    public String getKayttajanNimi() {
        return kayttaja.getUsername();
    }

    public Date getAika() {
        return aika;
    }

    public void setAika(Date aika) {
        this.aika = aika;
    }

    public String getSisalto() {
        return sisalto;
    }

    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }
    
}
