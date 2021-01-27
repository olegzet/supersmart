package com.olegzet.supersmart.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by oleg.zorin on 27.12.2017.
 */

@AllArgsConstructor
@Getter
public class ResponseError {
    private final String timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
}
