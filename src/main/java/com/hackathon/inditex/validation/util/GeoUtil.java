package com.hackathon.inditex.validation.util;

import org.springframework.stereotype.Component;

/**
 * Utility class for geographical calculations.
 */
@Component
public class GeoUtil {

    // Earth's radius in kilometers
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Calculates the great-circle distance between two points using the Haversine formula.
     *
     * @param latitude1 Latitude of the first point.
     * @param longitude1 Longitude of the first point.
     * @param latitude2 Latitude of the second point.
     * @param longitude2 Longitude of the second point.
     * @return The distance in kilometers.
     */
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double deltaLat = Math.toRadians(latitude2 - latitude1);
        double deltaLon = Math.toRadians(longitude2 - longitude1);

        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
                        Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
