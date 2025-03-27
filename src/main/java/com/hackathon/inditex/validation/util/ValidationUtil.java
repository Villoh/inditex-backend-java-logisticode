package com.hackathon.inditex.validation.util;

import com.hackathon.inditex.exception.BadRequestException;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.List;

@UtilityClass
public class ValidationUtil {

    /**
     * Validates that the given value is higher than the maximum allowed.
     *
     * @param actual The actual value to be validated.
     * @param max The maximum allowed value.
     * @param errorMessage The error message to be thrown if validation fails.
     * @throws BadRequestException If the actual value is not higher than the maximum allowed.
     */
    public static void validateIsHigherThan(Integer actual, Integer max, String errorMessage) throws BadRequestException {
        if(actual > max){
            throw new BadRequestException(errorMessage, null);
        }
    }

    /**
     * This method checks that a given object is not null, and if it is a String, it checks that the String is not blank.
     *
     * @param object the object to be validated
     * @param message the error message to be thrown if validation fails
     * @throws BadRequestException if the object is null or blank
     */
    public static void validateNotNullOrBlank(Object object, String message) throws BadRequestException{
        if(object == null){
            throw new BadRequestException(message, null);

        }
        if (object instanceof String string && !StringUtils.hasText(string)){
            throw new BadRequestException(message, null);
        }
    }

    /**
     * This method checks that a given list of objects is not null or contains any null or blank strings.
     *
     * @param objects the list of objects to be validated
     * @param message the error message to be thrown if validation fails
     * @throws BadRequestException if the object is null or blank
     */
    public static void validateNotNullOrBlankBulk(List<Object> objects, String message) throws BadRequestException{
        objects.forEach(object -> {
                    if(object == null){
                        throw new BadRequestException(message, null);

                    }
                    if (object instanceof String string && !StringUtils.hasText(string)){
                        throw new BadRequestException(message, null);
                    }
                });
    }
}
