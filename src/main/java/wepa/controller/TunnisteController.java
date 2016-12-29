/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import wepa.service.TunnisteService;

@Controller
public class TunnisteController {

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
        if(bindingResult.hasErrors()){
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
    @RequestMapping(value="/{kuvaId}/lisaatunniste", method = RequestMethod.POST)
    public String lisaaTunnisteKuvaan(@PathVariable Long kuvaId, @RequestParam Long tunnisteId) {
        tunnisteService.lisaatunnisteKuvaan(kuvaId, tunnisteId);
        return "redirect:/pics/"+kuvaId;
    }
    

    //Poistetaan tunniste kuvasta
    @RequestMapping(value="/{kuvaId}/poistatunniste/{tunnisteId}", method = RequestMethod.DELETE)
    public String poistaTunnisteKuvasta(@PathVariable Long kuvaId, @PathVariable Long tunnisteId) {
        tunnisteService.poistaTunnisteKuvasta(kuvaId, tunnisteId);
        return "redirect:/pics/"+kuvaId;
    }
    

    //Poistetaan tunniste kokonaan
    @RequestMapping(value="/tunnisteet/{tunnisteId}", method = RequestMethod.DELETE)
    public String deleteTunniste(@PathVariable Long tunnisteId) {
        tunnisteService.delete(tunnisteId);
        return "redirect:/tunnisteet";
    }

}
