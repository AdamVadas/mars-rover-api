package com.vadas.MarsRoverApi.service;

import com.vadas.MarsRoverApi.dto.HomeDTO;
import com.vadas.MarsRoverApi.response.MarsPhoto;
import com.vadas.MarsRoverApi.response.MarsRoverApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class MarsRoverApiService {

    private static final String API_KEY = "WMVtDK3FBBFRLVNnzHGTjdbAwC799x2pBXPz3jqM";

    private Map<String, List<String>> validCameras = new HashMap<>();

    public MarsRoverApiService() {
        validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
        validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "NAVCAM", "MARDI"));
        validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
    }

    public MarsRoverApiResponse getRoverData(HomeDTO homeDTO) throws InvocationTargetException, IllegalAccessException {
        RestTemplate rt = new RestTemplate();

        List<String> apiUrlEndpoints = getApiUrlEndpoints(homeDTO);
        List<MarsPhoto> photos = new ArrayList<>();
        MarsRoverApiResponse response = new MarsRoverApiResponse();

        apiUrlEndpoints.forEach(url -> {
            MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
            assert apiResponse != null;
            photos.addAll(apiResponse.getPhotos());
        });

        response.setPhotos(photos);
        return response;
    }

    public List<String> getApiUrlEndpoints(HomeDTO homeDTO) throws InvocationTargetException, IllegalAccessException {
        List<String> urls = new ArrayList<>();

        Method[] methods = homeDTO.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().contains("getCamera") && Boolean.TRUE.equals(method.invoke(homeDTO))) {
                String cameraName = method.getName().split("getCamera")[1].toUpperCase();
                if (validCameras.get(homeDTO.getMarsApiRoverData()).contains(cameraName)) {
                    urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDTO.getMarsApiRoverData()
                            + "/photos?sol=" + homeDTO.getMarsSol()
                            + "&api_key=" + API_KEY
                            + "&camera=" + cameraName);
                }
            }
        }
        return urls;
    }

    public Map<String, List<String>> getValidCameras() {
        return validCameras;
    }
}
