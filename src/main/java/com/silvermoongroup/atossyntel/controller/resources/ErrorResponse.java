package com.silvermoongroup.atossyntel.controller.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonIgnoreProperties({"proxyTargetClass", "exposeProxy", "preFiltered", "frozen"})
@Component
public class ErrorResponse {

    private List<String> errors = new ArrayList<String>();

    public void addError(String error) {
        getErrors().add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @JsonProperty
    public List<String> getErrors() {
        return errors;
    }
}
