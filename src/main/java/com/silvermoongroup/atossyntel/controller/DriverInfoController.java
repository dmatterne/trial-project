package com.silvermoongroup.atossyntel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.silvermoongroup.atossyntel.controller.resources.DriverInfoResponse;
import com.silvermoongroup.atossyntel.controller.resources.ErrorResponse;
import com.silvermoongroup.atossyntel.controller.resources.ResponseEntityErrorType;
import com.silvermoongroup.atossyntel.dao.DriverInfoRepository;
import com.silvermoongroup.atossyntel.domain.DriverInformation;
import com.silvermoongroup.atossyntel.helper.ResponseEntityExceptionHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping(value = "/api/driver_info")
public class DriverInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverInfoController.class);

    @Autowired
    private DriverInfoRepository driverInfoRepository;

    @Autowired
    protected ResponseEntityExceptionHelper responseEntityExceptionHelper;

    @ApiOperation(value = "Get Driver Information",  notes = "This ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The request was successful", response = DriverInfoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Invalid authorization Credentials", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server error occurred")})
    @GetMapping(
            params = {"firstName","lastName"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpEntity getDriverInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        DriverInfoResponse response = new DriverInfoResponse(false);

        if (firstName.isEmpty()|| lastName.isEmpty() ) return responseEntityExceptionHelper
                .getExceptionResponseEntity(ResponseEntityErrorType.FIRST_AND_LAST_NAME_NOT_PROVIDED, HttpStatus.BAD_REQUEST);

        List<DriverInformation> listOfDrivers = driverInfoRepository.findByFirstNameAndLastName(firstName, lastName);

        if (listOfDrivers == null)
            return responseEntityExceptionHelper
                .getExceptionResponseEntity(ResponseEntityErrorType.PERSON_NOT_FOUND, HttpStatus.BAD_REQUEST);

        if (listOfDrivers.size() == 0)
            return responseEntityExceptionHelper
                    .getExceptionResponseEntity(ResponseEntityErrorType.PERSON_NOT_FOUND, HttpStatus.BAD_REQUEST);

        response.setSuccess(true);
        response.setDriverInformation(listOfDrivers.get(0));

        return ResponseEntity.ok().body(response);

    }
}
