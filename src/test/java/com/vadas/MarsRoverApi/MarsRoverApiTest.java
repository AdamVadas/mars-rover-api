package com.vadas.MarsRoverApi;

import com.vadas.MarsRoverApi.response.MarsRoverApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MarsRoverApiTest {

    @Test
    public void smallTest() {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=2&api_key=WMVtDK3FBBFRLVNnzHGTjdbAwC799x2pBXPz3jqM", MarsRoverApiResponse.class);
        System.out.println(response.getBody());
    }
}
