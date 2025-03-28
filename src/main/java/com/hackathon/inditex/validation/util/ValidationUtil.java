package com.hackathon.inditex.validation.util;

import com.hackathon.inditex.exception.BadRequestException;
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
     * @throws BadRequestException If the actual value is not higher than the maximum allowed.
     */
    public static void validateIsHigherThan(Integer actual, Integer max, GenericException exception) throws BadRequestException {
        Optional.ofNullable(actual)
                .filter(value -> value > max)
                .ifPresent(value -> { throw exception; });
    }
}
