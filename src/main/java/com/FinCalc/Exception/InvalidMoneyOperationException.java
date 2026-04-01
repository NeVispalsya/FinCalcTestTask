package com.FinCalc.Exception;

public class InvalidMoneyOperationException extends RuntimeException {
    public InvalidMoneyOperationException(String message) {
        super(message);
    }
}
