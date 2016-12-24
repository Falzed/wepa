package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.domain.Kayttaja;
import wepa.repository.KayttajaRepository;

@Controller
@RequestMapping("/kayttajat")
public class KayttajaController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Kayttaja kayttaja) {
        kayttajaRepository.save(kayttaja);
        return "redirect:/login";
    }
}
