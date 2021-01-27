package com.olegzet.supersmart.service.validation;

import java.util.List;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public abstract class ValidationStep<T> {
    private ValidationStep<T> next;

    public ValidationStep<T> linkWith(ValidationStep<T> next) {
        this.next = next;
        return next;
    }

    public abstract void verify(T toValidate, List<ValidationResult> results);

    public void checkNext(T toValidate, List<ValidationResult> results) {
        if (next == null) {
            return;
        }
        next.verify(toValidate, results);
    }
}
