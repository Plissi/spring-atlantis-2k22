package com.example.backoffice.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SecurityController {
    @GetMapping(path = "/403")
    public String notAuthorized(){
        return "403";
    }
}
