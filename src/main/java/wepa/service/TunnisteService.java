package wepa.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa.domain.Kuva;
import wepa.domain.Tunniste;
import wepa.repository.KuvaRepository;
import wepa.repository.TunnisteRepository;

@Service
public class TunnisteService {
    @Autowired
    KuvaRepository kuvaRepository;
    
    @Autowired
    TunnisteRepository tunnisteRepository;
    
    public List<Tunniste> findAll() {
        return tunnisteRepository.findAll();
    }
    
    public Tunniste findOne(Long tunnisteId) {
        return tunnisteRepository.findOne(tunnisteId);
    }
    
    
    //Talletetaan tunniste repositorioon
    public void addTag(Tunniste tag) {
        tunnisteRepository.save(tag);
    }
    
    //Tunnisteen haku nimen perusteella
    public Tunniste findByNimi(String nimi) {
        return tunnisteRepository.findByNimi(nimi);
    }
    
    // Tunnisteen poistaminen
    @Transactional
    public void delete(Long tunnisteId) {
        Tunniste tunniste = tunnisteRepository.findOne(tunnisteId);
        for (Kuva kuva : tunniste.getKuvat()) {
            kuva.getTunnisteet().remove(tunniste);
        }
        
        tunnisteRepository.delete(tunnisteId);
    }
    
    //Tunnisteen lisääminen tietylle kuvalle
    public void lisaatunnisteKuvaan(Long kuvaId, Long tunnisteId) {
        if(tunnisteRepository.findOne(tunnisteId) == null ||
                kuvaRepository.findOne(kuvaId) == null) {
            return;
        }
        
        Kuva kuva = kuvaRepository.findOne(kuvaId);
        Tunniste tunniste = tunnisteRepository.findOne(tunnisteId);
        
        if (kuva.getTunnisteet().contains(tunniste)) {
            return;
        }
        
        kuva.getTunnisteet().add(tunniste);
        tunniste.getKuvat().add(kuva);
        
        kuvaRepository.save(kuva);
        tunnisteRepository.save(tunniste);
    }
    
    //Tunnisteen poistaminen tietyltä kuvalta
    public void poistaTunnisteKuvasta(Long kuvaId, Long tunnisteId) {
        Kuva kuva = kuvaRepository.findOne(kuvaId);
        Tunniste tunniste = tunnisteRepository.findOne(tunnisteId);
        
        if(!kuva.getTunnisteet().contains(tunniste)) {
            return;
        }
        
        kuva.getTunnisteet().remove(tunniste);
        tunniste.getKuvat().remove(kuva);
        
        kuvaRepository.save(kuva);
        tunnisteRepository.save(tunniste);
    }
}
