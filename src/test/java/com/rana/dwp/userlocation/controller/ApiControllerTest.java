package com.rana.dwp.userlocation.controller;

import com.rana.dwp.userlocation.api.User;
import com.rana.dwp.userlocation.service.UserLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserLocationService service;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_service_api_getUsersWithinFiftyMileslondon_api() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/within-fifty-miles-london")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_within_fifty_MilesOflondon_api_Returns_Data_list() throws Exception {

        User user = new User();

        user.setId(100);
        user.setFirstName("myfirstname");
        user.setLastName("mylastname");
        user.setEmail("mylastname.myfirstname@gmail.com");
        user.setIpAddress("255.255,255.1");
        user.setLatitude(50.123456);
        user.setLongitude(20.7654);

        Set<User> users = Set.of(user);


        when(service.usersWithInFifyMilesOdCity(anyString())).thenReturn(users);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/within-fifty-miles-london")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..users[0].id").value(user.getId()))
                .andExpect(jsonPath("$..users[0].first_name").value(user.getFirstName()))
                .andExpect(jsonPath("$..users[0].last_name").value(user.getLastName()))
                .andExpect(jsonPath("$..users[0].email").value(user.getEmail()))
                .andExpect(jsonPath("$..users[0].ip_address").value(user.getIpAddress()))
                .andExpect(jsonPath("$..users[0].latitude").value(user.getLatitude()))
                .andExpect(jsonPath("$..users[0].longitude").value(user.getLongitude()));

    }

    @Test
    public void test_within_fifty_MilesOflondon_api_Returns_with_fields() throws Exception {

        User user = new User();
        Set<User> users = Set.of(user);
        when(service.usersWithInFifyMilesOdCity(anyString())).thenReturn(users);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/within-fifty-miles-london")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users").exists())
                .andExpect(jsonPath("$..users[0]").exists())
                .andExpect(jsonPath("$.users").isArray())
                .andExpect(jsonPath("$..users[0].id").exists())
                .andExpect(jsonPath("$..users[0].first_name").exists())
                .andExpect(jsonPath("$..users[0].last_name").exists())
                .andExpect(jsonPath("$..users[0].email").exists())
                .andExpect(jsonPath("$..users[0].ip_address").exists())
                .andExpect(jsonPath("$..users[0].latitude").exists())
                .andExpect(jsonPath("$..users[0].longitude").exists())
        ;

    }

    @Test
    public void test_within_fifty_MilesOflondon_api_Returns_Empty_list() throws Exception {
        Set<User> users = new HashSet<>();
        when(service.usersWithInFifyMilesOdCity(anyString())).thenReturn(users);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/within-fifty-miles-london")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users").exists());

    }

}