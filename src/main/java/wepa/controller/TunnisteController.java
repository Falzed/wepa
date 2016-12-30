/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.domain.Tunniste;
import wepa.service.LoggedInKayttajaService;
import wepa.service.TunnisteService;

@Controller
public class TunnisteController {

    @Autowired
    LoggedInKayttajaService loggedInKayttajaService;
    @Autowired
    TunnisteService tunnisteService;

    //Haetaan tunnisteet
    @RequestMapping(value = "/tunnisteet", method = RequestMethod.GET)
    public String getTunnisteet(Model model) {
        model.addAttribute("tunnisteet", tunnisteService.findAll());
        model.addAttribute("tunniste", new Tunniste());
        return "tunnisteet";
    }

    //Luodaan uusi tunniste
    @RequestMapping(value = "/tunnisteet", method = RequestMethod.POST)
    public String lisaaTunniste(@Valid @ModelAttribute Tunniste tunniste,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tunniste", tunniste);
            model.addAttribute("tunnisteet", tunnisteService.findAll());
            return "tunnisteet";
        }
        if (tunnisteService.findByNimi(tunniste.getNimi()) == null) {
            tunnisteService.addTag(tunniste);
        }
        return "redirect:/tunnisteet";
    }

    //Liitetään tunniste kuvaan
    @RequestMapping(value = "/{kuvaId}/lisaatunniste", method = RequestMethod.POST)
    public String lisaaTunnisteKuvaan(@PathVariable Long kuvaId, @RequestParam Long tunnisteId) {
        tunnisteService.lisaatunnisteKuvaan(kuvaId, tunnisteId);
        return "redirect:/pics/" + kuvaId;
    }

    //Poistetaan tunniste kuvasta
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{kuvaId}/poistatunniste/{tunnisteId}", method = RequestMethod.DELETE)
    public String poistaTunnisteKuvasta(@PathVariable Long kuvaId, @PathVariable Long tunnisteId) {
        tunnisteService.poistaTunnisteKuvasta(kuvaId, tunnisteId);
        return "redirect:/pics/" + kuvaId;
    }

    // Haetaan tunnisteeseen liittyvät kuvat
    @RequestMapping(value = "/tunnisteet/{tunnisteId}", method = RequestMethod.GET)
    public String getTunniste(Model model, @PathVariable Long tunnisteId) {
        model.addAttribute("tunniste", tunnisteService.findOne(tunnisteId));
        return "tunniste";
    }

    //Poistetaan tunniste kokonaan
//    @Secured("ADMIN")
    @RequestMapping(value = "/tunnisteet/{tunnisteId}", method = RequestMethod.DELETE)
    public String deleteTunniste(@PathVariable Long tunnisteId) {
        if (this.loggedInKayttajaService.getAuthenticatedKayttaja().getAuthority().equals("ADMIN")) {
            tunnisteService.delete(tunnisteId);
        }
        return "redirect:/tunnisteet";
    }

}
