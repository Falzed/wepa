/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "tunnisteet";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String lisaaTunniste(@RequestParam String nimi) {
        if (tunnisteService.findByNimi(nimi) == null) {
            Tunniste tunniste = new Tunniste();
            tunniste.setNimi(nimi);
            tunnisteService.addTag(tunniste);
        }
        return "redirect:/tunnisteet";
    }

}
