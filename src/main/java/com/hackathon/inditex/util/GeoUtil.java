package com.hackathon.inditex.util;

import lombok.experimental.UtilityClass;

/**
 * Utility class for geographical calculations, specifically for calculating the distance
 * between two geographic coordinates using the Haversine formula.
 */
@UtilityClass
public class GeoUtil {

    /** Earth's radius in kilometers. */
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Calculates the great-circle distance between two points on Earth using the Haversine formula.
     *
     * @param latitude1 Latitude of the first point in degrees.
     * @param longitude1 Longitude of the first point in degrees.
     * @param latitude2 Latitude of the second point in degrees.
     * @param longitude2 Longitude of the second point in degrees.
     * @return The distance in kilometers.
     */
    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double deltaLat = toRadians(latitude2 - latitude1);
        double deltaLon = toRadians(longitude2 - longitude1);
        double lat1Rad = toRadians(latitude1);
        double lat2Rad = toRadians(latitude2);

        return haversineFormula(deltaLat, deltaLon, lat1Rad, lat2Rad);
    }

    /**
     * Computes the Haversine formula to determine the great-circle distance.
     *
     * @param deltaLat Difference in latitude in radians.
     * @param deltaLon Difference in longitude in radians.
     * @param lat1Rad Latitude of the first point in radians.
     * @param lat2Rad Latitude of the second point in radians.
     * @return The calculated distance in kilometers.
     */
    private static double haversineFormula(double deltaLat, double deltaLon, double lat1Rad, double lat2Rad) {
        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    /**
     * Converts degrees to radians.
     *
     * @param degrees The angle in degrees.
     * @return The angle in radians.
     */
    private static double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }
}