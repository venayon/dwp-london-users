package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.util.GeoDistanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserWithInRadiusServiceTest {

    @Mock
    private RestTemplate restTemplate;

    final String  userServiceUrl = "https://bpdts-test-app.herokuapp_temp.com";
    public static final String CITY_LONDON= "London";
    private String URI ;

    private UsersWithInRadiusService userWithInRadiusService;

    @InjectMocks
    private GeoDistanceService geoDistanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userWithInRadiusService = new UsersWithInRadiusService(userServiceUrl, restTemplate, geoDistanceService);
        URI = userServiceUrl + "/users";
    }


    @Test
    public void test_getUser_returns_EmptyList_BAD_Request1() {
        Double radius = 50.00;
       final double LONDON_LATITUDE = 51.50853;
        final double LONDON_LONGITUDE = -0.12574;

        User closeUser = new User();

        closeUser.setId(100);
        closeUser.setLatitude(51.50853);
        closeUser.setLongitude(-0.12574);
        //51.50853, -0.12574
        User[] closeUsers = {closeUser};
        ResponseEntity<User[]> responseEntity = new ResponseEntity<User[]>(closeUsers, HttpStatus.OK);
        when(restTemplate.exchange(URI, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> UserWithIn50MilesRadius = userWithInRadiusService.userWithInRadiusOfCoordinates(radius , LONDON_LATITUDE , LONDON_LONGITUDE);
        assertThat(UserWithIn50MilesRadius).hasSize(closeUsers.length);
    }

    @Test
    public void test_userWithInRadiusOfCoordinates_returns_EmptyList_BAD_Request() {
        Double radius = 50.00;
        Double latitude = 50.210;
        Double longitude = 10.210;
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        when(restTemplate.exchange(URI, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> users = userWithInRadiusService.userWithInRadiusOfCoordinates(radius , latitude , longitude);
        assertThat(users).isEmpty();
    }

}