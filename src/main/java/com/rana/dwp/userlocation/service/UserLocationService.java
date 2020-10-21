package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;

import java.util.List;

public interface UserLocationService {
    List<User> usersByCity(String anyString) ;
}
