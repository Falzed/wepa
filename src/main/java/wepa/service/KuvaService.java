package wepa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa.domain.Kayttaja;
import wepa.domain.Kommentti;
import wepa.domain.Kuva;
import wepa.repository.KuvaRepository;

@Service
public class KuvaService {

    @Autowired
    KuvaRepository kuvaRepository;
    
    @Autowired
    private LoggedInKayttajaService loggedInKayttajaService;

    public Kuva findOne(Long id) {
        return kuvaRepository.findOne(id);
    }

    public List<Kuva> findAll() {
        return kuvaRepository.findAll();
    }

    public List<Kuva> findByAccount() {
        return kuvaRepository.findByKayttaja(loggedInKayttajaService.getAuthenticatedKayttaja());
    }
    
    public void save(Kuva kuva) {
        kuva.setKayttaja(loggedInKayttajaService.getAuthenticatedKayttaja());
        kuvaRepository.save(kuva);
    }
    
    public List<Kommentti> getKuvanKommentit(Long id) {
        return kuvaRepository.findOne(id).getKommentit();
    }
    
    public void deleteKuva(Long id) {
        kuvaRepository.delete(id);
    }
}
