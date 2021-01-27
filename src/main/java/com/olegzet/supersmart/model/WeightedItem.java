package com.olegzet.supersmart.model;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public class WeightedItem extends TransactionItem {
    public WeightedItem(String id, String barcode, int weight) {
        super(id, barcode, weight);
    }
}
