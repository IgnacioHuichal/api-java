package cl.lupoconecta.apijava.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String root() {
        // nombre de la plantilla html ubicada en src/main/resources/templates/index.html
        return "index";
    }
}
