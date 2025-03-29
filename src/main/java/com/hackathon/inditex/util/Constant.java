package com.hackathon.inditex.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    @UtilityClass
    public static class Center{
        public static final String DELETED_CENTER = "Logistics center deleted successfully.";
        public static final String UPDATED_CENTER = "Logistics center updated successfully.";
        public static final String SUCCESSFUL_CREATION_MESSAGE = "Logistics center created successfully.";

        public static final String ALREADY_EXISTS_CENTER_IN_AREA_MESSAGE = "There is already a logistics center in that position.";
        public static final String MAX_CAPACITY_EXCEEDED_MESSAGE = "Current load cannot exceed max capacity.";
        public static final String INVALID_INPUT_FIELDS = "Invalid input fields.";
        public static final String CENTER_NOT_FOUND = "Center not found.";
        public static final String STATUS_AVAILABLE = "AVAILABLE";
    }

    @UtilityClass
    public static class Order {
        public static final String ORDER_CREATED = "Order created successfully in PENDING status.";
        public static final String NO_AVAILABLE_CENTERS = "No available centers support the order type.";
        public static final String ALL_CENTERS_FULL = "All centers are at maximum capacity.";

        public static final String PENDING_STATUS = "PENDING";
        public static final String ASSIGNED_STATUS = "ASSIGNED";
    }

    @UtilityClass
    public static class Api {
        public static final String API_BASE_PATH = "/api";
        public static final String ORDERS_ENDPOINT = API_BASE_PATH + "/orders";
        public static final String CENTERS_ENDPOINT = API_BASE_PATH + "/centers";
    }
}
