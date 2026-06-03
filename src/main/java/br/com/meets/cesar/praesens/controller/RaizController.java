package br.com.meets.cesar.praesens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class RaizController {

    @GetMapping("/")
    public String redirecionarParaSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}