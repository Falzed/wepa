package wepa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;

@Service
public class KayttajaService {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    public boolean checkIfUsernameUnique(String inputUsername) {
        boolean check = true;
        
        for (Kayttaja kayttaja : kayttajaRepository.findAll()) {
            if (kayttaja.getUsername().equals(inputUsername)) {
                check = false;
            }
        }
        
        return check;
    }
    
}
