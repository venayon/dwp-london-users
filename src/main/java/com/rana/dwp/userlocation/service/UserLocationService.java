package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.util.GeoDistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserLocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLocationService.class);
    private static final double CITY_RADIUS = 50D; // miles from city
    public static final double LONDON_LATITUDE = 51.50853;
    public static final double LONDON_LONGITUDE = -0.12574;
    private final UserFromCityService userFromCityService;
    private final UsersWithInRadiusService usersWithInRadiusService;
    private final GeoDistanceService geoDistanceService;

    @Autowired
    public UserLocationService(UserFromCityService userFromCityService,
                               UsersWithInRadiusService usersWithInRadiusService , GeoDistanceService geoDistanceService) {
        this.userFromCityService = userFromCityService;
        this.usersWithInRadiusService = usersWithInRadiusService;
        this.geoDistanceService = geoDistanceService;

    }

    /**
     *
     * @param city : London
     * @return returns all london listed and users with in 50 miles of london
     */
    public List<User> usersWithInFifyMilesOdCity(String city) {
        LOGGER.info("fetching london users");
        List<User> cityUser = userFromCityService.usersByCity(city);
        LOGGER.info("Fetched London users {} fetching usersWithInRadius ", cityUser.size());
        //add london listed users to out set
        List<User> usersWithInRadius = usersWithInRadiusService.userWithInRadiusOfCoordinates( CITY_RADIUS , LONDON_LATITUDE, LONDON_LONGITUDE );
        LOGGER.info("Fetched London usersWithInRadius {} ", usersWithInRadius.size());
       return Stream.of(usersWithInRadius, cityUser).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }

}