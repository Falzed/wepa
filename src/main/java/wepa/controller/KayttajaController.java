package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;

@Controller
@RequestMapping("/kayttajat")
public class KayttajaController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    //Käyttäjätunnuksen luominen
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Kayttaja kayttaja) {
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
