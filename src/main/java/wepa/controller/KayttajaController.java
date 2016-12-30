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

@Controller
public class KayttajaController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute Kayttaja kayttaja) {
        return "register";
    }

    
    //Käyttäjätunnuksen luominen
<<<<<<< HEAD
    @RequestMapping(value = "/kayttajat", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute Kayttaja kayttaja, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
=======
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Kayttaja kayttaja) {
        kayttaja.setAuthority("USER");
>>>>>>> 0eb66ef586798461b5b8834a46e3e0b3a7c50f62
        kayttajaRepository.save(kayttaja);
        return "redirect:/login";
    }
    
    // Käyttäjälistan näyttäminen
<<<<<<< HEAD
    //@Secured("ADMIN")
    @RequestMapping(value = "/kayttajat", method = RequestMethod.GET)
=======
    @Secured("ADMIN")
    @RequestMapping(method = RequestMethod.GET)
>>>>>>> 0eb66ef586798461b5b8834a46e3e0b3a7c50f62
    public String viewAll(Model model) {
        model.addAttribute("kayttajat", kayttajaRepository.findAll());
        return "kayttajat";
    }
    
<<<<<<< HEAD
=======
    @Secured("ADMIN")
    @RequestMapping(value = "/poista/{kayttajaId}", method = RequestMethod.GET)
    public String deleteKayttaja(@PathVariable Long kayttajaId) {
        return "kayttajat";
    }
    
>>>>>>> 0eb66ef586798461b5b8834a46e3e0b3a7c50f62
}
