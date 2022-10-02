package ru.ivanov.transportation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ivanov.transportation.service.InjectRandomInt;

@Controller
public class HomeController {

    @InjectRandomInt(min = 1, max = 10)
    private int trucksAvailable;

    @ModelAttribute(name = "trucksAvailable")
    public int manager() {
        return trucksAvailable;
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
