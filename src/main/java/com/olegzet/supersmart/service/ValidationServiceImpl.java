package com.olegzet.supersmart.service;

import com.olegzet.supersmart.exception.EmptyTransactionException;
import com.olegzet.supersmart.model.Transaction;
import com.olegzet.supersmart.model.TransactionItem;
import com.olegzet.supersmart.service.validation.GreenItemsBarcodeNotStartWith29Validation;
import com.olegzet.supersmart.service.validation.TotalWeightAbove40kgValidation;
import com.olegzet.supersmart.service.validation.TotalWeightWeightedItemsLess10kgValidation;
import com.olegzet.supersmart.service.validation.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Transaction transaction) {
        List<TransactionItem> items = transaction.getItems();
        if (items.isEmpty()) {
            throw new EmptyTransactionException("Transaction must have items for validating");
        }

        List<ValidationResult> results = new LinkedList<>();

        TotalWeightAbove40kgValidation chain = new TotalWeightAbove40kgValidation();
        chain.linkWith(new TotalWeightWeightedItemsLess10kgValidation())
                .linkWith(new GreenItemsBarcodeNotStartWith29Validation());
        chain.verify(transaction, results);
        return results.stream().filter(ValidationResult::isValid).count() >= 2;
    }
}
