package com.rana.dwp.userlocation.util;

import org.springframework.stereotype.Service;

@Service
public class GeoDistanceService {

    // Earth Radius 6371 Kms
    private final static Double EARTH_RADIUS = 3958.756; //miles

    /**
     * Returns distance between the two Coordinates(Latitude,Longitude )
     *
     * @param originLatitude  : User latitude
     * @param originLongitude : user Longitude
     * @param userLatitude    :  Search Location Coordinates - Latitude
     * @param userLongiitude  :  Search Location Coordinates Longiitude
     * @return distince between user and city Coordinates
     */
    public double distanceBetween(final double originLatitude, final double originLongitude,
                                  final double userLatitude, final double userLongiitude) {


        double oriLon = Math.toRadians(originLongitude); // loc
        double oriLat = Math.toRadians(originLatitude);

        double usrlong = Math.toRadians(userLongiitude); //user
        double usrlat = Math.toRadians(userLatitude);

        double lonDist = oriLon - usrlong;
        double latDist = oriLat - usrlat;

        double a = Math.pow(Math.sin(latDist / 2), 2)
                + Math.cos(usrlat) * Math.cos(oriLat)
                * Math.pow(Math.sin(lonDist / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS * c;


    }

}