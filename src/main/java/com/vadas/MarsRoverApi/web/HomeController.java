package com.vadas.MarsRoverApi.web;

import com.vadas.MarsRoverApi.dto.HomeDTO;
import com.vadas.MarsRoverApi.response.MarsRoverApiResponse;
import com.vadas.MarsRoverApi.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.InvocationTargetException;

@Controller
public class HomeController {

    @Autowired
    private MarsRoverApiService roverService;

    @GetMapping("/")
    public String getHomeView(ModelMap model, HomeDTO homeDTO) throws InvocationTargetException, IllegalAccessException {
        if (StringUtils.isEmpty(homeDTO.getMarsApiRoverData())) {
            homeDTO.setMarsApiRoverData("Opportunity");
        }
        if (homeDTO.getMarsSol() == null) {
            homeDTO.setMarsSol(1);
        }
        MarsRoverApiResponse roverData = roverService.getRoverData(homeDTO);
        model.put("roverData", roverData);
        model.put("homeDTO", homeDTO);
        model.put("validCameras", roverService.getValidCameras().get(homeDTO.getMarsApiRoverData()));

        return "index";
    }
}
