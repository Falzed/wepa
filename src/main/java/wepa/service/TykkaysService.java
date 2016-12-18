package wepa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa.domain.Kayttaja;
import wepa.domain.Kuva;
import wepa.domain.Tykkays;
import wepa.repository.KuvaRepository;
import wepa.repository.TykkaysRepository;

@Service
public class TykkaysService {
    @Autowired
    private LoggedInKayttajaService loggedInKayttajaService;
    @Autowired
    private TykkaysRepository tykkaysRepository;
    @Autowired
    private KuvaRepository kuvaRepository;
    
    public Tykkays tallennaTykkays(Kuva kuva) {
        Kayttaja kayttaja = loggedInKayttajaService.getAuthenticatedKayttaja();
        for(Tykkays tykkays : kuva.getTykkaykset()) {
            if(tykkays.getKayttaja() == kayttaja) {
                return null;
            }
        }
            
        Tykkays tykkays = new Tykkays();
        tykkays = tykkaysRepository.save(tykkays);
        
        tykkays.setKayttaja(kayttaja);
        tykkays.setKuva(kuva);
        
        kuva.getTykkaykset().add(tykkays);
        tykkaysRepository.save(tykkays);
        kuvaRepository.save(kuva);
        
        return tykkays;
    }
    
    public int tykkayksia(Long id) {
        return kuvaRepository.findOne(id).getTykkaykset().size();
    }
    
}