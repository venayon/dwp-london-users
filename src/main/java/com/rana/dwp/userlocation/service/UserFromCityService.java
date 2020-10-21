package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class UserFromCityService {

    @Autowired
    private RestTemplate restTemplate;
    
    
    private final String userServiceUrl;

    final String CITY_LONDON = "London";
    @Autowired
    public UserFromCityService(@Value("${user.location.api.url}") String userServiceUrl ,
                               RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }
    
    
    public List<User> usersByCity(String city) {

        final String uri = userServiceUrl + "/city/" + CITY_LONDON+ "/users";

        final ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, User[].class);

        return HttpStatus.OK.equals(response.getStatusCode()) && response.getBody()!= null ? Arrays.asList(response.getBody()) : new ArrayList<>();
    }


}
