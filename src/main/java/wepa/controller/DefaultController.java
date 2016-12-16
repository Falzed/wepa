package wepa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @RequestMapping("*")
    public String redirect() {
        return "redirect:/pics";
    }
}
