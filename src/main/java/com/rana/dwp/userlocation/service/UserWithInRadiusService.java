package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.util.GeoDistanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserWithInRadiusService {

    private final RestTemplate restTemplate;

    private final String userServiceUrl;

    private final GeoDistanceServiceImpl geoDistanceService;

    @Autowired
    public UserWithInRadiusService(final @Value("${user.location.api.url}") String userServiceUrl,
                                   RestTemplate restTemplate,
                                   GeoDistanceServiceImpl geoDistanceService)  {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
        this.geoDistanceService = geoDistanceService;
    }


    public List<User> userWithInRadiusOfCoordinates(Double radius, Double latitude, Double longitude) {

        final String uri = userServiceUrl + "/users";

        final ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, User[].class);

        return HttpStatus.OK.equals(response.getStatusCode()) && response.getBody()!= null ? Arrays.asList(response.getBody()) : new ArrayList<>();
    }
}
