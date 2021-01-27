package com.olegzet.supersmart.exception;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public abstract class ValidationAppException extends RuntimeException {
    public ValidationAppException(String message) {
        super(message);
    }
}
