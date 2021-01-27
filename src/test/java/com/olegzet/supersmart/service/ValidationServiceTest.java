package com.olegzet.supersmart.service;

import com.olegzet.supersmart.exception.EmptyTransactionException;
import com.olegzet.supersmart.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {
    @InjectMocks
    private ValidationServiceImpl validationService;

    @Test
    public void testSuccessValidation() {
        Transaction transaction = new Transaction();
        List<TransactionItem> items = transaction.getItems();
        items.add(new UnitItem("11", "012345678912", 11));
        items.add(new UnitItem("12", "012345678912", 11));
        items.add(new UnitItem("13", "012345678912", 11));
        items.add(new UnitItem("14", "012345678912", 11));
        items.add(new WeightedItem("21", "01234567891234", 3));
        items.add(new WeightedItem("22", "01234567891234", 3));
        items.add(new WeightedItem("23", "01234567891234", 3));
        items.add(new GreenItem("31", "012345678912", 20));
        items.add(new GreenItem("32", "012345678912", 20));
        items.add(new GreenItem("33", "012345678912", 20));
        items.add(new GreenItem("34", "012345678912", 20));
        items.add(new GreenItem("35", "012345678912", 20));
        Assertions.assertTrue(validationService.validate(transaction));
    }

    @Test
    public void testUnSuccessValidation() {
        Transaction transaction = new Transaction();
        List<TransactionItem> items = transaction.getItems();
        items.add(new UnitItem("1", "012345678912", 8));
        items.add(new WeightedItem("2", "012345678912", 11));
        items.add(new GreenItem("3", "292345678912", 20));
        Assertions.assertFalse(validationService.validate(transaction));
    }

    @Test
    public void testValidationException() {
        assertThrows(EmptyTransactionException.class, () -> validationService.validate(new Transaction()));
    }
}
