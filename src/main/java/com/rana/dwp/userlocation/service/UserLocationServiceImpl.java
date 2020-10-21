package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserLocationServiceImpl implements UserLocationService {

    private final UserFromCityService userFromCity;

    @Autowired
    public UserLocationServiceImpl(UserFromCityService userFromCity) {
        this.userFromCity = userFromCity;
    }

    public List<User> usersByCity(String city) {
        return userFromCity.usersByCity(city);
    }

}
