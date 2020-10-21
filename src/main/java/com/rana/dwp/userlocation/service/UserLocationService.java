package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserLocationService {

    private final UserFromCityService userFromCityService;
    private final UsersWithInRadiusService usersWithInRadiusService;

    @Autowired
    public UserLocationService(UserFromCityService userFromCityService, UsersWithInRadiusService usersWithInRadiusService) {
        this.userFromCityService = userFromCityService;
        this.usersWithInRadiusService = usersWithInRadiusService;
    }

    public Set<User> usersWithInFifyMilesOdCity(String city) {
        Set<User> users = new HashSet<>();
        return users;
    }
}
