package com.FinCalc;

public class InvalidMoneyOperationException extends RuntimeException {
    public InvalidMoneyOperationException(String message) {
        super(message);
    }
}
