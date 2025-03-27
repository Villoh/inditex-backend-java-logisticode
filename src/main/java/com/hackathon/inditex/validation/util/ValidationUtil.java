package com.hackathon.inditex.validation.util;

import com.hackathon.inditex.exception.BadRequestException;
import com.hackathon.inditex.exception.GenericException;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.List;
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

    /**
     * Checks that a given list of objects is not null and does not contain null or blank strings.
     *
     * @param objects the list of objects to be validated
     * @param exception the error message to be thrown if validation fails
     * @throws BadRequestException if any object is null or blank
     */
    public static void validateNotNullOrBlankBulk(List<Object> objects, GenericException exception) throws GenericException {
        Optional.ofNullable(objects)
                .orElseThrow(() -> exception)
                .stream()
                .filter(obj -> obj == null || (obj instanceof String string && !StringUtils.hasText(string)))
                .findFirst()
                .ifPresent(obj -> { throw exception; });
    }
}
