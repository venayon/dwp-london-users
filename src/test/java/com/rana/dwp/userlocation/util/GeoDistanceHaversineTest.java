package com.rana.dwp.userlocation.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class GeoDistanceHaversineTest {


   @InjectMocks
   private GeoDistanceService distanceService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void distanceBetween_ForSameCoordinates_Returns_Zero_distance(){

        double orginlatitude = 50.210;
        double orginlongitude = 10.210;

        double destinationlatitude = 50.210;
        double destinationlongitude = 10.210;

        double distance = distanceService.distanceBetween(orginlatitude,orginlongitude , destinationlatitude, destinationlongitude);
        assertThat(distance).isEqualTo(0);

    }

}