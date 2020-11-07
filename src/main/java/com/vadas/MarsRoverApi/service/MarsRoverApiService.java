package com.vadas.MarsRoverApi.service;

import com.vadas.MarsRoverApi.response.MarsRoverApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarsRoverApiService {

    private static final String API_KEY = "WMVtDK3FBBFRLVNnzHGTjdbAwC799x2pBXPz3jqM";

    public MarsRoverApiResponse getRoverData(String roverType, Integer marsSol) {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/" + roverType + "/photos?sol=" + marsSol + "&api_key=" + API_KEY, MarsRoverApiResponse.class);
        return response.getBody();
    }


}
