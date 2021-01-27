package com.olegzet.supersmart.service.validation;

import com.olegzet.supersmart.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */
@ExtendWith(MockitoExtension.class)
public class TotalWeightAbove40kgValidationTest {
    @InjectMocks
    private TotalWeightAbove40kgValidation validation;

    @Test
    public void testSuccessValidation() {
        List<ValidationResult> results = new LinkedList<>();
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
        validation.verify(transaction, results);
        Assertions.assertEquals(1, results.stream().filter(ValidationResult::isValid).count());
    }

    @Test
    public void testValidationException1() {
        assertThrows(NullPointerException.class, () -> validation.verify(new Transaction(), null));
    }

    @Test
    public void testValidationException2() {
        assertThrows(NullPointerException.class, () -> validation.verify(null, new LinkedList<>()));
    }

    @Test
    public void testUnSuccessValidation() {
        List<ValidationResult> results = new LinkedList<>();
        Transaction transaction = new Transaction();
        List<TransactionItem> items = transaction.getItems();
        items.add(new UnitItem("11", "012345678912", 11));
        validation.verify(transaction, results);
        Assertions.assertEquals(0, results.stream().filter(ValidationResult::isValid).count());
        Assertions.assertEquals(1, results.stream().filter(item -> !item.isValid()).count());
        Assertions.assertEquals("Total weight of items in transaction is less than 40 kilograms",
                results.stream().filter(item -> !item.isValid()).findFirst().get().getErrorMsg());
    }
}
