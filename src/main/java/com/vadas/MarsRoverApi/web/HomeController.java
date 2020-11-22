package com.vadas.MarsRoverApi.web;

import com.vadas.MarsRoverApi.dto.HomeDTO;
import com.vadas.MarsRoverApi.response.MarsRoverApiResponse;
import com.vadas.MarsRoverApi.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;

@Controller
public class HomeController {

    @Autowired
    private MarsRoverApiService roverService;

    @GetMapping("/")
    public String getHomeView(ModelMap model, Long userId, Boolean createUser) throws InvocationTargetException, IllegalAccessException {

        HomeDTO homeDTO = createDefaultHomeDTO(userId);

        if (Boolean.TRUE.equals(createUser) && userId == null) {
            homeDTO = roverService.save(homeDTO);
        } else {
            homeDTO = roverService.findbyUserId(userId);
            if (homeDTO == null) {
                homeDTO = createDefaultHomeDTO(userId);
            }
        }

        MarsRoverApiResponse roverData = roverService.getRoverData(homeDTO);
        model.put("roverData", roverData);
        model.put("homeDTO", homeDTO);
        model.put("validCameras", roverService.getValidCameras().get(homeDTO.getMarsApiRoverData()));
        if (!Boolean.TRUE.equals(homeDTO.getRememberPreferences()) && userId != null) {
            HomeDTO defaultHomeDTO = createDefaultHomeDTO(userId);
            roverService.save(defaultHomeDTO);
        }

        return "index";
    }

    @GetMapping("/savedPreferences")
    @ResponseBody
    public HomeDTO getSavedPreferences(Long userId) {
        if (userId != null) {
            return roverService.findbyUserId(userId);
        } else {
            return createDefaultHomeDTO(userId);
        }
    }

    private HomeDTO createDefaultHomeDTO(Long userId) {
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setMarsApiRoverData("Opportunity");
        homeDTO.setMarsSol(1);
        homeDTO.setUserId(userId);
        return homeDTO;
    }

    @PostMapping("/")
    public String postHomeView(HomeDTO homeDTO) {
        homeDTO = roverService.save(homeDTO);
        return "redirect:/?userId=" + homeDTO.getUserId();
    }
}
