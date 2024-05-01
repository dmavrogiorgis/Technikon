package gr.scytalys.team3.Technikon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public void Home(){
        System.out.println("Home page");
    }

    @GetMapping("/contactUs")
    public void Contact(){
        System.out.println("Contact Us");
    }
}
