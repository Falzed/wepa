package wepa.domain;
 
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Kuva extends AbstractPersistable<Long> {
 
    @Lob
    private byte[] content;
    
    @ManyToOne
    private Kayttaja kayttaja;
 
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
}