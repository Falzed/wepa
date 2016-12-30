package wepa.controller;

import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wepa.domain.Kommentti;
import wepa.domain.Kuva;
import wepa.repository.KommenttiRepository;
import wepa.repository.KuvaRepository;
import wepa.service.KuvaService;
import wepa.service.LoggedInKayttajaService;
import wepa.service.TykkaysService;
import wepa.service.TunnisteService;

@Controller
@RequestMapping("/pics")
public class KuvaController {

    @Autowired
    private LoggedInKayttajaService loggedInKayttajaService;
    
    @Autowired
    private KuvaService kuvaService;
    
    @Autowired
    private TykkaysService tykkaysService;
    
    @Autowired
    private TunnisteService tunnisteService;

    //Haetaan kaikki kuvat
    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String viewOwnPictures(Model model) {
        model.addAttribute("kuvat", kuvaService.findByAccount());
        model.addAttribute("count", kuvaService.findAll().size());
        return "pics";
    }
    
    //Haetaan yksi tietty kuva
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewOne(Model model, @PathVariable Long id) {
        model.addAttribute("kuva", kuvaService.findOne(id));
        model.addAttribute("kommentit", kuvaService.getKuvanKommentit(id));
        model.addAttribute("tykkaykset", tykkaysService.tykkayksia(id));
        model.addAttribute("kommentti", new Kommentti());
        model.addAttribute("tunnisteet", tunnisteService.findAll());
        return "pic";
    }

    //gif pitäisi toimia myös muille kuville
    @Transactional
    @RequestMapping(value = "/{id}/content", method = RequestMethod.GET, produces = "image/gif")
    @ResponseBody
    public byte[] viewData(@PathVariable Long id) {
        return kuvaService.findOne(id).getContent();
    }


    //Otetaan kuva vastaan ja talletetaan se
    @RequestMapping(method = RequestMethod.POST)
    public String post(@RequestParam MultipartFile file) throws IOException {
        if (!file.getContentType().startsWith("image/")) {
            return "redirect:/pics";
        }
        Kuva kuva = new Kuva();
        kuva.setContent(file.getBytes());
        kuvaService.save(kuva);

        return "redirect:/pics";
    }
    
    //Kuvan poistaminen
//    @Secured("ADMIN")
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public String deletePicture(@PathVariable Long id) {
        if (this.loggedInKayttajaService.getAuthenticatedKayttaja().getAuthority().equals("ADMIN")) {
        kuvaService.deleteKuva(id);
        }
        return "redirect:/pics";
    }
    
}
