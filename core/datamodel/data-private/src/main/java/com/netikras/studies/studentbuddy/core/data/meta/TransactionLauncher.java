package com.netikras.studies.studentbuddy.core.data.meta;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionLauncher {

    @Transactional
    public void executeTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.execute();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeSeparateTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.execute();
        }
    }

    public static abstract class Transaction {
        public abstract void execute();
    }

}
