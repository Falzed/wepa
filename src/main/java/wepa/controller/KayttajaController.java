package wepa.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;

@Controller
public class KayttajaController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute Kayttaja kayttaja) {
        return "register";
    }

    
    //Käyttäjätunnuksen luominen
    @RequestMapping(value = "/kayttajat", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute Kayttaja kayttaja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        kayttajaRepository.save(kayttaja);
        return "redirect:/login";
    }
    
    // Käyttäjälistan näyttäminen
    //@Secured("ADMIN")
    @RequestMapping(value = "/kayttajat", method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("kayttajat", kayttajaRepository.findAll());
        return "kayttajat";
    }
    
}
