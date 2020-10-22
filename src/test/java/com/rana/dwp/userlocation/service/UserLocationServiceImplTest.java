package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.util.GeoDistanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

public class UserLocationServiceImplTest {

    @Mock
    private UserLocationService userLocationService;

    @Mock
    private UserFromCityService userFromCityService;

    @Mock
    private UsersWithInRadiusService usersWithInRadiusService;

    @InjectMocks
    private GeoDistanceService geoDistanceService;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userLocationService = new UserLocationService( userFromCityService, usersWithInRadiusService,geoDistanceService );
    }

    @Test
    public void test_usersWithInFifyMilesOdCity_Both_Services_ReturnsEmptyList() {
        String city = "London";


        when(userFromCityService.usersByCity(city)).thenReturn(new ArrayList<>());
        when(usersWithInRadiusService.userWithInRadiusOfCoordinates(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(new ArrayList<>());
        List<User> usersWithInFifyMilesOdCity = userLocationService.usersWithInFifyMilesOdCity(city);
        assertThat(usersWithInFifyMilesOdCity).isEmpty();
    }



    @Test
    public void test_usersWithInFifyMilesOdCity_Both_Services_NOLondonUser() {
        String city = "London";
        User farUser = createUser(100, 60.50853 , -77.12574);
        when(userFromCityService.usersByCity(city)).thenReturn(new ArrayList<>());
        when(usersWithInRadiusService.userWithInRadiusOfCoordinates(anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(new ArrayList<>());
        List<User> usersWithInFifyMilesOdCity = userLocationService.usersWithInFifyMilesOdCity(city);

        assertThat(usersWithInFifyMilesOdCity).isEmpty();
    }

    @Test
    public void test_usersWithInFifyMilesOdCity_Returns_ListedLondonUsers() {
        String city = "London";
        User lonUsr1 = createUser(100, 51.50853 , -0.12574);
        User lonUsr2 = createUser(200, 56.50853 , -0.12574);
        List<User> londonListedUsers = List.of(lonUsr1,lonUsr2);
        when(userFromCityService.usersByCity(city)).thenReturn(londonListedUsers);
        List<User> usersWithInFifyMilesOdCity = userLocationService.usersWithInFifyMilesOdCity(city);
       assertThat(usersWithInFifyMilesOdCity).hasSize(londonListedUsers.size());
    }

    @Test
    public void test_uusersWithInFifyMilesOdCity_Return_EmptyUserList(){
        String city = "London";
        when(userFromCityService.usersByCity(city)).thenReturn(new ArrayList<>());
        List<User> usersWithInFifyMilesOdCity = userLocationService.usersWithInFifyMilesOdCity(city);
        assertNotNull(usersWithInFifyMilesOdCity);
    }

    @Test
    public void test_usersWithInFifyMilesOdCity_Return() {
        String city = "London";
        List<User> usersWithInFifyMilesOdCity = userLocationService.usersWithInFifyMilesOdCity(city);
        assertNotNull(usersWithInFifyMilesOdCity);
    }


    private User createUser(int id, double latitude, double longitude) {
        User user = new User();
        user.setId(id);
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        return user;
    }
}
