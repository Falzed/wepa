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
 
@Controller
@RequestMapping("/pics")
public class KuvaController {
 
    @Autowired
    private KuvaRepository kuvaRepository;
    
    @Autowired
    private KommenttiRepository kommenttiRepository;
 
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable Long id) {
        Long imageCount = kuvaRepository.count();
        model.addAttribute("count", imageCount);
 
        if (id >= 1L && id <= imageCount) {
            model.addAttribute("current", id);
            model.addAttribute("comments", kuvaRepository.findOne(id).getKommentit());
        }
 
        if (id < imageCount && id > 0L) {
            model.addAttribute("next", id + 1);
        }
 
        if (id > 1L) {
            model.addAttribute("previous", id - 1);
        }
 
        return "pics";
    }
 
    @RequestMapping(method = RequestMethod.GET)
    public String viewFiles() {
        return "redirect:/pics/1";
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public String addFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.getContentType().startsWith("image/")) {
            return "redirect:/pics";
        }
 
        Kuva gifObject = new Kuva();
        gifObject.setContent(file.getBytes());
        kuvaRepository.save(gifObject);
 
        return "redirect:/pics";
    }
 
    @RequestMapping(value = "{id}/content", method = RequestMethod.GET, produces = "image/gif")
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        return kuvaRepository.findOne(id).getContent();
    }
    
    @RequestMapping(value = "pic/{id}", method = RequestMethod.GET, produces = "image/gif")
    public String getPic(Model model, @PathVariable Long id) {
        model.addAttribute("kuva", kuvaRepository.findOne(id).getContent());
        model.addAttribute("current", id);
        model.addAttribute("kommentit", kommenttiRepository.findAll());
        model.addAttribute("tykkaykset", 0);
        return "pic";
    }
    
}