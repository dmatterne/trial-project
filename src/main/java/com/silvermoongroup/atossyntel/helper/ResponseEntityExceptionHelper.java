package com.silvermoongroup.atossyntel.helper;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import com.silvermoongroup.atossyntel.controller.resources.ErrorResponse;
import com.silvermoongroup.atossyntel.controller.resources.ResponseEntityErrorType;

@Component
public class ResponseEntityExceptionHelper {

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected ErrorResponse errorResponse;

    private static final String DEFAULT_EXCEPTION_NAME = "An exception occurred.";
    private static final String DEFAULT_EXCEPTION_MESSAGE = "An exception occurred.";
    private static final String DEFAULT_EXCEPTION_PROPERTY = "mobile.quote.error.general";


    public HttpEntity getExceptionResponseEntity(Exception e) {

        return this.getExceptionResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    public HttpEntity getExceptionResponseEntity(ResponseEntityErrorType type, HttpStatus httpStatus, String... arg) {

        String name = DEFAULT_EXCEPTION_NAME;
        String defaultMessage = DEFAULT_EXCEPTION_MESSAGE;
        String property = DEFAULT_EXCEPTION_PROPERTY;
        ObjectError objectError;

        if (type != null) {

            name = type.getName();
            defaultMessage = type.getDefaultMessage();
            property = type.getProperty();

        }

        if (arg == null) {
            objectError = createObjectError(name, defaultMessage,
                                            new String[]{property},
                                            new String[]{}
            );
        } else {
            objectError = createObjectError(name, defaultMessage,
                                            new String[]{property},
                                            arg
            );
        }

        errorResponse.addError(messageSource.getMessage(objectError, Locale.ENGLISH));
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    public ResponseEntity<ErrorResponse> getErrorResponseEntity(
            BeanPropertyBindingResult result) {
        return getErrorResponseEntityWithStatus(result, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> getErrorResponseEntityWithStatus(
            BeanPropertyBindingResult result, HttpStatus httpStatus) {
        List<ObjectError> objectErrors = result.getAllErrors();
        for (ObjectError objectError : objectErrors) {
            errorResponse.addError(messageSource.getMessage(objectError, Locale.ENGLISH));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    public ObjectError createObjectError(String name, String defaultMessage, String codes[], String args[]) {
        return new ObjectError(name, codes, args, defaultMessage);
    }

}
