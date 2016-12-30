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
import org.springframework.web.bind.annotation.RequestParam;
import wepa.domain.Kommentti;
import wepa.service.KommenttiService;
import wepa.service.KuvaService;
import wepa.service.TykkaysService;

@Controller
public class KommenttiController {

    @Autowired
    private KommenttiService kommenttiService;
    
    @Autowired
    private KuvaService kuvaService;
    
    @Autowired
    private TykkaysService tykkaysService;

    //Kommentin luonti, tallennus ja kuvaan liittäminen
    @RequestMapping(value = "/pics/{id}/comment", method = RequestMethod.POST)
    public String postKommentti(@PathVariable Long id, @Valid @ModelAttribute Kommentti kommentti,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("kommentti", kommentti);
            model.addAttribute("kuva", kuvaService.findOne(id));
            model.addAttribute("kommentit", kuvaService.getKuvanKommentit(id));
            model.addAttribute("tykkaykset", tykkaysService.tykkayksia(id));
            return "pic";
        }
        kommenttiService.postKommentti(id, kommentti.getSisalto());
        return "redirect:/pics/{id}";
    }


    //Kommentin poistaminen
//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/pics/{id}/{kommenttiId}", method = RequestMethod.DELETE)
    public String deleteKommentti(@PathVariable Long id, @PathVariable Long kommenttiId) {
        kommenttiService.deleteKommentti(id, kommenttiId);
        return "redirect:/pics/{id}";
    }

}
