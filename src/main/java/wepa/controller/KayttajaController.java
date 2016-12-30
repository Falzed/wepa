package wepa.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;
import wepa.service.KayttajaService;

@Controller
@RequestMapping("/kayttajat")
public class KayttajaController {
    
    @Autowired
    private KayttajaService kayttajaService;
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute Kayttaja kayttaja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || (kayttajaService.checkIfUsernameUnique(kayttaja.getUsername()) == false)) {
            
            if (kayttajaService.checkIfUsernameUnique(kayttaja.getUsername()) == false) {
                model.addAttribute("notUnique", "Käyttäjänimi on varattu");
            }
            
            return "register";
        }

        kayttaja.setAuthority("USER");
        kayttajaRepository.save(kayttaja);
        return "redirect:/login";
    }
    
    // Käyttäjälistan näyttäminen
    @Secured("ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("kayttajat", kayttajaRepository.findAll());
        return "kayttajat";
    }
    
    @Secured("ADMIN")
    @RequestMapping(value = "/poista/{kayttajaId}", method = RequestMethod.GET)
    public String deleteKayttaja(@PathVariable Long kayttajaId) {
        return "kayttajat";
    }
    
}
