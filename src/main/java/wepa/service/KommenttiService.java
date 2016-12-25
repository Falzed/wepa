package wepa.service;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa.domain.Kayttaja;
import wepa.domain.Kommentti;
import wepa.domain.Kuva;
import wepa.repository.KommenttiRepository;
import wepa.repository.KuvaRepository;

@Service
public class KommenttiService {
    
    @Autowired
    private KommenttiRepository kommenttiRepository;
    
    @Autowired
    private KuvaRepository kuvaRepository;
    
    //Talletetaan kuvakohtainen kommentti
    public Kommentti postKommentti(Long kuvaId, String sisalto) {
        Kuva kuva = kuvaRepository.findOne(kuvaId);
        Kommentti kommentti = new Kommentti();
        kommentti.setSisalto(sisalto);
        
        Kayttaja kt = new Kayttaja();
        kt.setUsername("testiKayttaja");
        kommentti.setKayttaja(kt);
        
        kommentti = kommenttiRepository.save(kommentti);
        
        kuva.getKommentit().add(kommentti);
        kommentti.setKuva(kuva);
        
        kuvaRepository.save(kuva);
        kommentti = kommenttiRepository.save(kommentti);
        
        return kommentti;
    }
    

    //Poistetaan kuvakohtainen kommentti
    @Transactional
    public void deleteKommentti(Long kuvaId, Long kommenttiId) {
        Kommentti kommentti = kommenttiRepository.findOne(kommenttiId);
        kuvaRepository.findOne(kuvaId).getKommentit().remove(kommentti);
        kommenttiRepository.delete(kommentti);
    }
    
}
