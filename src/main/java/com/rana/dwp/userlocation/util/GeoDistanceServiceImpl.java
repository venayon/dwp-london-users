package com.rana.dwp.userlocation.util;

import com.rana.dwp.userlocation.api.Coordinates;
import org.springframework.stereotype.Service;

/**
 * py code
 * lat1, lon1 = origin
 * lat2, lon2 = destination
 * radius = 6371  # km
 * <p>
 * dlat = math.radians(lat2 - lat1)
 * dlon = math.radians(lon2 - lon1)
 * a = (math.sin(dlat / 2) * math.sin(dlat / 2) +
 * math.cos(math.radians(lat1)) * math.cos(math.radians(lat2)) *
 * math.sin(dlon / 2) * math.sin(dlon / 2))
 * c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
 * d = radius * c
 * <p>
 * return d
 */
@Service
public class GeoDistanceServiceImpl {

    // Earth Radius
    final static Double EARTH_RADIUS = 6371D;

    /**
     * Returns distance between the two Coordinates(Latitude,Longitude )
     * @param origin : Users Coordinates
     * @param destination :  Search Location Coordinates
     * @return distince between user and city Coordinates
     */
    public Double distanceBetween(Coordinates origin, Coordinates destination) {

        var lat1 = origin.getLatitude();
        var lon1 = origin.getLongitude();

        var lat2 = destination.getLatitude();
        var lon2 = destination.getLongitude();
        var radius = 6371;

        var dlat = Math.toRadians(lat2 - lat1);
        var dlon = Math.toRadians(lon2 - lon1);

        var a = (Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dlon / 2) * Math.sin(dlon / 2));

        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;


    }

}