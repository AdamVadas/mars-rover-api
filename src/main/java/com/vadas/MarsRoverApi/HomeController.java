package com.vadas.MarsRoverApi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @RequestMapping(path = "/r", method = RequestMethod.GET)
    public String getHomeView (ModelMap model) {
        model.put("name", "Trevor Page");
        return "index";
    }
}
