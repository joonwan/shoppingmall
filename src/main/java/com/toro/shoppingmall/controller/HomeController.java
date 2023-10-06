package com.toro.shoppingmall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){

        log.info("call home controller");
        return "home";
    }
}
