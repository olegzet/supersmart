package com.olegzet.supersmart.service.validation;

import com.olegzet.supersmart.model.GreenItem;
import com.olegzet.supersmart.model.Transaction;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public class GreenItemsBarcodeNotStartWith29Validation extends ValidationStep<Transaction> {
    @Override
    public void verify(Transaction toValidate, List<ValidationResult> results) {
        Validate.notNull(toValidate);
        Validate.notNull(results);

        boolean verificationResult = (toValidate.getItems().stream().filter(item -> item instanceof GreenItem)
                .noneMatch(item -> StringUtils.isNotEmpty(item.getBarcode()) && item.getBarcode().startsWith("29")));
        results.add(verificationResult ? ValidationResult.valid()
                : ValidationResult.invalid("Green items in the transactions have barcode starting with ‘29’"));

        checkNext(toValidate, results);
    }
}
