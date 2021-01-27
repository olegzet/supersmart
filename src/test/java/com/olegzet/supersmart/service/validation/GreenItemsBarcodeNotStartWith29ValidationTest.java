package com.olegzet.supersmart.service.validation;

import com.olegzet.supersmart.model.GreenItem;
import com.olegzet.supersmart.model.Transaction;
import com.olegzet.supersmart.model.TransactionItem;
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
public class GreenItemsBarcodeNotStartWith29ValidationTest {
    @InjectMocks
    private GreenItemsBarcodeNotStartWith29Validation validation;

    @Test
    public void testSuccessValidation() {
        List<ValidationResult> results = new LinkedList<>();
        Transaction transaction = new Transaction();
        List<TransactionItem> items = transaction.getItems();
        items.add(new GreenItem("31", "012345678912", 20));
        items.add(new GreenItem("32", "012345678912", 20));
        items.add(new GreenItem("33", null, 20));
        items.add(new GreenItem("34", "012345678912", 20));
        items.add(new GreenItem("35", null, 234550));
        items.add(new GreenItem("36", "012345678912", 20));
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
        items.add(new GreenItem("31", "012345678912", 22340));
        items.add(new GreenItem("33", "292345678912", 20));
        validation.verify(transaction, results);
        Assertions.assertEquals(0, results.stream().filter(ValidationResult::isValid).count());
        Assertions.assertEquals(1, results.stream().filter(item -> !item.isValid()).count());
        Assertions.assertEquals("Green items in the transactions have barcode starting with ‘29’",
                results.stream().filter(item -> !item.isValid()).findFirst().get().getErrorMsg());
    }
}
