package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Set;

public class UserLocationServiceImplTest {

    @InjectMocks
    private UserLocationService userLocationService;

    @InjectMocks
    private UserFromCityService userFromCityService;

    @InjectMocks
    private UsersWithInRadiusService usersWithInRadiusService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userLocationService = new UserLocationService(userFromCityService, usersWithInRadiusService);
    }

    @Test
    public void test_usersByCity_Return() {
        String city = "London";
        Set<User> users = userLocationService.usersWithInFifyMilesOdCity(city);
        Assertions.assertNotNull(users);
    }

    @Test
    public void test_usersByCity_Return_EmptyUserList(){
        String city = "London";
        Set<User> users = userLocationService.usersWithInFifyMilesOdCity(city);
        Assertions.assertNotNull(users);
    }
}
