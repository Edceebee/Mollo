package com.Mollo.assessment.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponses {

    /**
     * field for api message.
     */
    private String message;
    /**
     * field to check if its successful.
     */
    private boolean isSuccessful;
    /**
     * field for the time of response.
     */
    private LocalDateTime timeStamp;


    /**
     * Constructor for class.
     * @param message represents message to be shown.
     * @param isSuccessful to check if it is successful.
     */
    public ApiResponses(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.timeStamp = LocalDateTime.now();
    }


    /**
     * constructor for only message.
     * @param message represents message to be shown.
     */
    public ApiResponses(String message) {
        this.message = message;
    }

}
