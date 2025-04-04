package com.hackathon.inditex.validation.util;

import com.hackathon.inditex.exception.GenericException;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ValidationUtil {

    /**
     * Validates that the given value is higher than the maximum allowed.
     *
     * @param actual The actual value to be validated.
     * @param max The maximum allowed value.
     * @param exception the error message to be thrown if validation fails
     */
    public static void validateIsHigherThan(Integer actual, Integer max, GenericException exception) {
        Optional.ofNullable(actual)
                .filter(value -> value > max)
                .ifPresent(value -> { throw exception; });
    }

    public static boolean isSmallerThan(Integer actual, Integer min) {
        return actual < min;
    }
}
