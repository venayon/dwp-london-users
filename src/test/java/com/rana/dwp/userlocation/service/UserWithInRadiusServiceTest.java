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
        Double lon_latitude = 50.123456;
        Double lon_longitude = 20.7654;

        User closeUser = new User();
        closeUser.setId(100);
        closeUser.setLatitude(51.50853);
        closeUser.setLongitude(-0.12574);
        //51.50853, -0.12574

        User[] londonUsers = {closeUser};
        User[] london = Utils.allusers();
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(london, HttpStatus.OK);
        when(restTemplate.exchange(URI, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> UserWithIn50MilesRadius = userWithInRadiusService.userWithInRadiusOfCoordinates(radius , lon_latitude , lon_longitude);

       // assertThat(UserWithIn50MilesRadius).hasSize(londonUsers.length);
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