package wepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.domain.Kayttaja;

@Controller
public class DefaultController {
    @RequestMapping("*")
    public String redirect() {
        return "redirect:/pics";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute Kayttaja kayttaja) {
        return "register";
    }

}
