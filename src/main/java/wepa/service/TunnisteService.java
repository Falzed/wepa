package wepa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    
    public void addTag(Tunniste tag) {
        tunnisteRepository.save(tag);
    }
    
    public Tunniste findByNimi(String nimi) {
        return tunnisteRepository.findByNimi(nimi);
    }
    
    public void delete(Long id) {
        tunnisteRepository.delete(id);
    }
}
