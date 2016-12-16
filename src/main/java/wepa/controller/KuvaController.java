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
import wepa.repository.KuvaRepository;
import wepa.service.KuvaService;

@Controller
@RequestMapping("/pics")
public class KuvaController {

    @Autowired
    private KuvaService kuvaService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewOwnPictures(Model model) {
        model.addAttribute("kuvat", kuvaService.findByAccount());
        return "pics";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewOne(Model model, @PathVariable Long id) {
        model.addAttribute("kuva", kuvaService.findOne(id));
        return "picture";
    }

    //gif pitäisi toimia myös muille kuville
    @RequestMapping(value = "/{id}/content", method = RequestMethod.GET, produces = "image/gif")
    @ResponseBody
    public byte[] viewData(@PathVariable Long id) {
        return kuvaService.findOne(id).getContent();
    }

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
}
