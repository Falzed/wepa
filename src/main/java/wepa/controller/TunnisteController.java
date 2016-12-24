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
@RequestMapping("tunnisteet")
public class TunnisteController {

    @Autowired
    TunnisteService tunnisteService;

    @RequestMapping(method = RequestMethod.GET)
    public String getTunnisteet(Model model) {
        model.addAttribute("tunnisteet", tunnisteService.findAll());
        model.addAttribute("tunniste", new Tunniste());
        return "tunnisteet";
    }

    @RequestMapping(method = RequestMethod.POST)
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
    
    @RequestMapping(value="/{tunnisteId}/{kuvaId}", method = RequestMethod.POST)
    public String lisaaTunnisteKuvaan(@PathVariable Long tunnisteId, @PathVariable Long kuvaId) {
        tunnisteService.lisaatunnisteKuvaan(tunnisteId, kuvaId);
        return "redirect:/pics/"+kuvaId;
    }
    
    @RequestMapping(value="/{tunnisteId}/{kuvaId}", method = RequestMethod.DELETE)
    public String poistaTunnisteKuvasta(@PathVariable Long tunnisteId, @PathVariable Long kuvaId) {
        tunnisteService.poistaTunnisteKuvasta(tunnisteId, kuvaId);
        return "redirect:/pics/"+kuvaId;
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteTunniste(@PathVariable Long id) {
        tunnisteService.delete(id);
    }

}
