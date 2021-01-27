package com.olegzet.supersmart.exception;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public class EmptyTransactionException extends ValidationAppException {
    public EmptyTransactionException(String message) {
        super(message);
    }
}
