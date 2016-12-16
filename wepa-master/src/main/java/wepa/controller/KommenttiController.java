package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.service.KommenttiService;

@Controller
public class KommenttiController {
    
    @Autowired
    private KommenttiService kommenttiService;
    
    @RequestMapping(value = "/pics/pic/{id}", method = RequestMethod.POST)
    public String postKommentti(@PathVariable Long id, @RequestParam String sisalto) {
        kommenttiService.postKommentti(id, sisalto);
        return "redirect:/pics/pic/{id}";
    }
    
    @RequestMapping(value = "/pics/{id}/{kommenttiId}", method = RequestMethod.DELETE)
    public String deleteKommentti(@PathVariable Long id, @PathVariable Long kommenttiId) {
        kommenttiService.deleteKommentti(id, kommenttiId);
        return "redirect:/pics/pic/{id}";
    }
    
}