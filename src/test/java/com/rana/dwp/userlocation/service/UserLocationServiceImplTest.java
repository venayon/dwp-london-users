package com.rana.dwp.userlocation.service;

import com.rana.dwp.userlocation.api.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

public class UserLocationServiceImplTest {

   /* @InjectMocks
    private UserLocationServiceImpl userLocationService;

    @InjectMocks
    private UserFromCity londonUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    public static final String CITY_LONDON= "London";

    @Test
    public void test_usersByCity_Return_UserList(){
       String city = "London";
       User user = new User();
       List<User> mockusres = List.of(user);
       when(londonUserService.usersByCity(CITY_LONDON)).thenReturn(mockusres);
       List<User> users = userLocationService.usersByCity(city);
       assertThat(users).contains(user);
    }


    @Test
    public void test_usersByCity_Return_EmptyUserList(){
        String city = "London";
        List<User> users = userLocationService.usersByCity(city);
        Assertions.assertNotNull(users);
    }
*/

}
