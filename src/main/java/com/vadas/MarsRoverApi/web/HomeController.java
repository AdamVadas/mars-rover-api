package com.vadas.MarsRoverApi.web;

import com.vadas.MarsRoverApi.dto.HomeDTO;
import com.vadas.MarsRoverApi.response.MarsRoverApiResponse;
import com.vadas.MarsRoverApi.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MarsRoverApiService roverService;

    @GetMapping("/")
    public String getHomeView(ModelMap model, HomeDTO homeDTO) {
        if (StringUtils.isEmpty(homeDTO.getMarsApiRoverData())) {
            homeDTO.setMarsApiRoverData("Opportunity");
        }
        if (homeDTO.getMarsSol() == null) {
            homeDTO.setMarsSol(1);
        }
        MarsRoverApiResponse roverData = roverService.getRoverData(homeDTO.getMarsApiRoverData(), homeDTO.getMarsSol());
        model.put("roverData", roverData);
        model.put("homeDTO", homeDTO);
        return "index";
    }
}
