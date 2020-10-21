package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UsersFromCityTest {

    @Mock
    private RestTemplate restTemplate;
    String  userServiceUrl = "https://bpdts-test-app.herokuapp_temp.com";

    private UserFromCityService londonUserService;
    public static final String CITY_LONDON= "London";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        londonUserService = new UserFromCityService(userServiceUrl, restTemplate);
    }

    @Test
    public void test_getUsers_ValidUserList(){
        User user = new User();
        User[]  mockuser = {user};
        String uri = userServiceUrl + "/city/" + CITY_LONDON + "/users";
        when(restTemplate.exchange(uri, HttpMethod.GET, null, User[].class)).thenReturn(new ResponseEntity<>(mockuser, HttpStatus.OK));
        List<User> users = londonUserService.usersByCity(CITY_LONDON);
        assertThat(users).isNotEmpty();
    }


    @Test
    public void test_getUser_returns_EmptyList_BAD_Request() {
        final String uri = userServiceUrl + "/city/" + CITY_LONDON+ "/users";
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       when(restTemplate.exchange(uri, HttpMethod.GET, null, User[].class)).thenReturn(responseEntity);
        List<User> users = londonUserService.usersByCity(CITY_LONDON);
        assertThat(users).isEmpty();
    }
}