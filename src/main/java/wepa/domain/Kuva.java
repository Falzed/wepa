package wepa.domain;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Kuva extends AbstractPersistable<Long> {
 
    @Lob
    private byte[] content;
    
    @ManyToOne
    private Kayttaja kayttaja;
    @OneToMany(mappedBy = "kuva")
    private List<Kommentti> kommentit;
   
    @OneToMany(mappedBy = "kuva")
    private List<Tykkays> tykkaykset;
    
    @ManyToMany(mappedBy = "kuvat")
    private List<Tunniste> tunnisteet;
 
    public byte[] getContent() {
        return content;
    }
 
    public void setContent(byte[] content) {
        this.content = content;
    }
    
    public Kayttaja getKayttaja() {
        return kayttaja;
    }
 
    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public List<Kommentti> getKommentit() {
        if (this.kommentit == null) {
            this.kommentit = new ArrayList<>();
        }
        return kommentit;
    }

    public void setKommentit(List<Kommentti> kommentit) {
        this.kommentit = kommentit;
    }
    
    public List<Tykkays> getTykkaykset() {
        if (this.tykkaykset == null) {
            this.tykkaykset = new ArrayList<>();
        }
        return tykkaykset;
    }

    public void setTykkaykset(List<Tykkays> tykkaykset) {
        this.tykkaykset = tykkaykset;
    }
    
    public List<Tunniste> getTunnisteet() {
        if (this.tunnisteet == null) {
            this.tunnisteet = new ArrayList<>();
        }
        return tunnisteet;
    }
}