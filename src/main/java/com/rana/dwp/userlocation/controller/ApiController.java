package com.rana.dwp.userlocation.controller;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.api.UserSearchLocationResult;
import com.rana.dwp.userlocation.service.UserLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserLocationService service;

    @GetMapping(value = "/users/within-fifty-miles-london")
    public ResponseEntity<UserSearchLocationResult> getLondonUsers() {

        LOGGER.info("getLondonUsers");

        Set<User> users = service.usersWithInFifyMilesOdCity("london");

        UserSearchLocationResult result = new UserSearchLocationResult();
        result.setUsers(users);
        return ResponseEntity.ok().body(result);

    }


}
