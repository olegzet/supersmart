package com.olegzet.supersmart.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@Getter
public class Transaction {
    List<TransactionItem> items = new ArrayList<>();
}
