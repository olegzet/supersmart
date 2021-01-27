package com.olegzet.supersmart.service.validation;

import com.olegzet.supersmart.model.Transaction;
import com.olegzet.supersmart.model.TransactionItem;
import com.olegzet.supersmart.model.WeightedItem;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public class TotalWeightWeightedItemsLess10kgValidation extends ValidationStep<Transaction> {
    @Override
    public void verify(Transaction toValidate, List<ValidationResult> results) {
        Validate.notNull(toValidate);
        Validate.notNull(results);

        boolean verificationResult = (toValidate.getItems().stream().filter(item -> item instanceof WeightedItem)
                .map(TransactionItem::getWeight).reduce(Integer::sum).orElse(-1) <= 10);
        results.add(verificationResult ? ValidationResult.valid()
                : ValidationResult.invalid("Weighted items in the transactions have total weight above 10 kilograms"));

        checkNext(toValidate, results);
    }
}
