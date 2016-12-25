package wepa.service;

import java.util.List;
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
    
    //Talletetaan tunniste repositorioon
    public void addTag(Tunniste tag) {
        tunnisteRepository.save(tag);
    }
    
    //Tunnisteen haku nimen perusteella
    public Tunniste findByNimi(String nimi) {
        return tunnisteRepository.findByNimi(nimi);
    }
    
    public void delete(Long id) {
        tunnisteRepository.delete(id);
    }
    
    //Tunnisteen lisääminen tietylle kuvalle
    public void lisaatunnisteKuvaan(Long tunnisteId, Long kuvaId) {
        if(tunnisteRepository.findOne(tunnisteId) == null ||
                kuvaRepository.findOne(kuvaId) == null) {
            return;
        }
        Kuva kuva = kuvaRepository.findOne(kuvaId);
        kuva.getTunnisteet().add(tunnisteRepository.findOne(tunnisteId));
        kuvaRepository.save(kuva);
    }
    
    //Tunnisteen poistaminen tietyltä kuvalta
    public void poistaTunnisteKuvasta(Long tunnisteId, Long kuvaId) {
        Kuva kuva = kuvaRepository.findOne(kuvaId);
        if(!kuva.getTunnisteet().contains(tunnisteRepository.findOne(tunnisteId))) {
            return;
        }
        kuva.getTunnisteet().remove(tunnisteRepository.findOne(tunnisteId));
        kuvaRepository.save(kuva);
    }
}
