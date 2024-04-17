package com.fastcampus.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class HomeController {

    @RequestMapping(value = "/list.co", method = RequestMethod.GET)
    public String list(Locale locale, Model model) {

        //모델값에 담으면 .jsp에 값이 담기는거 확인할수있다.
        model.addAttribute("url", "list.co" );
        System.out.println("model = " + model);
        return "list.tiles";
    }

    @RequestMapping(value = "/detail.co", method = RequestMethod.GET)
    public String detail(Locale locale, Model model) {

        model.addAttribute("url", "detail.co" );
        return "detail.tiles";
    }
}