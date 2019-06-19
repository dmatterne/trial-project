package com.silvermoongroup.atossyntel.controller.resources;

public enum ResponseEntityErrorType {

    PERSON_NOT_FOUND(
            "Person with given first and last name has not been found.",
            "person.not-found.by-first-name-and-last-name"
    ),
    FIRST_AND_LAST_NAME_NOT_PROVIDED("Sorry, First name and Last name are required and must be provided.",
                                     "person.invalid-input.missing-first-name-and-last-name");

    private String name;
    private String defaultMessage;
    private String property;


    ResponseEntityErrorType(String name, String defaultMessage, String property) {
        this.name = name;
        this.defaultMessage = defaultMessage;
        this.property = property;
    }

    ResponseEntityErrorType(String name, String property) {
        this.name = name;
        this.defaultMessage = name;
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getProperty() {
        return property;
    }
}