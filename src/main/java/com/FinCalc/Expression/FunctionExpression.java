package com.FinCalc.Expression;

import com.FinCalc.Money.CurrencyConverter;
import com.FinCalc.Money.Money;

public class FunctionExpression implements Expression {
    private String functionName;
    private Expression argument;
    private CurrencyConverter converter;

    public FunctionExpression(String functionName, Expression argument, CurrencyConverter converter) {
        this.functionName = functionName;
        this.argument = argument;
        this.converter = converter;
    }

    @Override
    public Money evaluate() {
        Money argValue = argument.evaluate();
        switch (functionName) {
            case "toDollar":
                return converter.toDollar(argValue);
            case "toEuro":
                return converter.toEuro(argValue);
            default:
                throw new RuntimeException("Unknown function: " + functionName);
        }
    }
}
