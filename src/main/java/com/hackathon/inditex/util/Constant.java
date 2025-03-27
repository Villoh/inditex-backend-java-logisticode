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
    }
}
