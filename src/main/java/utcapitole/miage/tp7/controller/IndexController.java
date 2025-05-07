package utcapitole.miage.tp7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller pour la page d'accueil
 */
@Controller
public class IndexController {
    
    /**
     * Affiche la page d'accueil
     * @return index.html
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }    
}