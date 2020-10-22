package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.util.GeoDistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;

@Service
public class UsersWithInRadiusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersWithInRadiusService.class);
    private final RestTemplate restTemplate;
    private final String userServiceUrl;
    private final GeoDistanceService geoDistanceService;


    @Autowired
    public UsersWithInRadiusService(final @Value("${user.location.api.url}") String userServiceUrl,
                                    final RestTemplate restTemplate,
                                    final GeoDistanceService geoDistanceService) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
        this.geoDistanceService = geoDistanceService;
    }

    public List<User> userWithInRadiusOfCoordinates(final Double radius, final Double latitude, final Double longitude) {
        final String uri = userServiceUrl + "/users";
        final ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, User[].class);
        List<User> allUsers = HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null ?
                Arrays.asList(response.getBody()) : new ArrayList<>();
        LOGGER.info("filter-out users 50 miles away from London");
        return allUsers.stream().parallel()
                .filter(u -> geoDistanceService.distanceBetween(latitude, longitude, u.getLatitude(), u.getLongitude()) <= radius).collect(Collectors.toList());
    }
}
