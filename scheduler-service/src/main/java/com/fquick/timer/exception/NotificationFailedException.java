package com.fquick.timer.exception;

import lombok.Data;

/**
 * Created by shlok.chaurasia on 07/03/16.
 */
@Data
public class NotificationFailedException extends Exception {
    private String message;

    public NotificationFailedException(String message) {
        super(message);
    }
}
