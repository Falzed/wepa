package wepa.controller;
 
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wepa.domain.Kuva;
import wepa.repository.KommenttiRepository;
import wepa.repository.KuvaRepository;
import wepa.service.KuvaService;
import wepa.service.TykkaysService;
 
@Controller
public class TykkaysController {
    
    @Autowired
    private KuvaService kuvaService;
    
    @Autowired
    private TykkaysService tykkaysService;
    

    //Kuvasta tykkääminen
    @RequestMapping(value = "pics/{id}/tykkaa", method = RequestMethod.POST)
    public String tykkaa(@PathVariable Long id) {
        Kuva kuva = kuvaService.findOne(id);
        tykkaysService.tallennaTykkays(kuva);
        return "redirect:/pics/{id}";
    }
    

    //Tykkäyksen poistaminen kuvasta
    @RequestMapping(value = "pics/{id}/tykkaa", method = RequestMethod.DELETE)
    public String poistaTykkays(@PathVariable Long id) {
        Kuva kuva = kuvaService.findOne(id);
        tykkaysService.poistaTykkays(kuva);
        return "redirect:/pics/{id}";
    }
}
