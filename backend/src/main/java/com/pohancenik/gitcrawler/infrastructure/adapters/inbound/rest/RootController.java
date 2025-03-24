package com.pohancenik.gitcrawler.infrastructure.adapters.inbound.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String root() {
        return "redirect:/swagger-ui.html";
    }

}
