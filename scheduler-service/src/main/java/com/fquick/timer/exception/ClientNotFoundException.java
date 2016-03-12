package com.fquick.timer.exception;

/**
 * Created by shlok.chaurasia on 06/03/16.
 */
public class ClientNotFoundException extends Exception {
    private String clientId;

    public ClientNotFoundException(String clientId) {
        this.clientId = clientId;
    }
}
