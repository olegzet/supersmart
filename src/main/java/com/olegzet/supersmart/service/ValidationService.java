package com.olegzet.supersmart.service;

import com.olegzet.supersmart.model.Transaction;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

public interface ValidationService {
    boolean validate(Transaction transaction);
}
