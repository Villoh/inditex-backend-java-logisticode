package com.hackathon.inditex.validation.util;

import com.hackathon.inditex.exception.BadRequestException;
import com.hackathon.inditex.exception.GenericException;
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
     * @param exception the error message to be thrown if validation fails
     * @throws BadRequestException If the actual value is not higher than the maximum allowed.
     */
    public static void validateIsHigherThan(Integer actual, Integer max, GenericException exception) throws BadRequestException {
        if(actual > max){
            throw exception;
        }
    }

    /**
     * This method checks that a given object is not null, and if it is a String, it checks that the String is not blank.
     *
     * @param object the object to be validated
     * @param exception the error message to be thrown if validation fails
     * @throws BadRequestException if the object is null or blank
     */
    public static void validateNotNullOrBlank(Object object, GenericException exception) throws GenericException {
        if(object == null){
            throw exception;

        }
        if (object instanceof String string && !StringUtils.hasText(string)){
            throw exception;
        }
    }

    /**
     * This method checks that a given list of objects is not null or contains any null or blank strings.
     *
     * @param objects the list of objects to be validated
     * @param exception the error message to be thrown if validation fails
     * @throws BadRequestException if the object is null or blank
     */
    public static void validateNotNullOrBlankBulk(List<Object> objects, GenericException exception) throws GenericException{
        objects.forEach(object -> {
                    if(object == null){
                        throw exception;

                    }
                    if (object instanceof String string && !StringUtils.hasText(string)){
                        throw exception;
                    }
                });
    }

    public static void validateRegex(String actual, String regexPattern, GenericException exception) throws GenericException{
        if(!actual.matches(regexPattern)){
            throw exception;
        }
    }
}
