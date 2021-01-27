package com.olegzet.supersmart.service.validation;

import com.olegzet.supersmart.model.Transaction;
import com.olegzet.supersmart.model.TransactionItem;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public class TotalWeightAbove40kgValidation extends ValidationStep<Transaction> {
    @Override
    public void verify(Transaction toValidate, List<ValidationResult> results) {
        Validate.notNull(toValidate);
        Validate.notNull(results);

        boolean verificationResult = (toValidate.getItems().stream()
                .map(TransactionItem::getWeight).reduce(Integer::sum).orElse(-1) > 40);
        results.add(verificationResult ? ValidationResult.valid()
                : ValidationResult.invalid("Total weight of items in transaction is less than 40 kilograms"));

        checkNext(toValidate, results);
    }
}
