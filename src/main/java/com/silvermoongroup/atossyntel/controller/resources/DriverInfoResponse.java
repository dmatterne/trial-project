package com.silvermoongroup.atossyntel.controller.resources;

import java.util.ArrayList;
import java.util.List;

import com.silvermoongroup.atossyntel.domain.DriverInformation;

public class DriverInfoResponse {

    private boolean success;
    private List<String> warningMessages = new ArrayList<String>();
    private DriverInformation driverInformation;

    public DriverInfoResponse() {
    }

    public DriverInfoResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getWarningMessages() {
        return warningMessages;
    }

    public void setWarningMessages(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public DriverInformation getDriverInformation() {
        return driverInformation;
    }

    public void setDriverInformation(DriverInformation driverInformation) {
        this.driverInformation = driverInformation;
    }
}
