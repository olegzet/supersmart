package com.olegzet.supersmart.service.validation;

import lombok.Value;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@Value
public class ValidationResult {
    boolean isValid;
    String errorMsg;

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(String errorMsg) {
        return new ValidationResult(false, errorMsg);
    }
}
