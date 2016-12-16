package wepa.domain;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Kuva extends AbstractPersistable<Long> {
 
    @Lob
    private byte[] content;
    
    @OneToMany(mappedBy = "kuva", fetch = FetchType.EAGER)
    private List<Kommentti> kommentit;
 
    public byte[] getContent() {
        return content;
    }
 
    public void setContent(byte[] content) {
        this.content = content;
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
    
}