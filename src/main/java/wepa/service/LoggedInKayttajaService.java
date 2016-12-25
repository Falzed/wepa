package wepa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;

@Service
public class LoggedInKayttajaService {

    @Autowired
    private KayttajaRepository kayttajaRepository;

    //Haetaan ja palautetaan käyttäjä, kunhan oikeudet kunnossa.
    public Kayttaja getAuthenticatedKayttaja() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return kayttajaRepository.findByUsername(authentication.getName());
    }
}
