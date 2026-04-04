package com.FinCalc.Expression;

import com.FinCalc.Exception.InvalidMoneyOperationException;
import com.FinCalc.Money.CurrencyConverter;
import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.TokenType;

public class OperationExpression implements Expression {
    private Expression left;
    private Expression right;
    private TokenType operator;
    private CurrencyConverter converter;

    public OperationExpression(Expression left, Expression right, TokenType operator,CurrencyConverter converter) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.converter = converter;
    }

    @Override
    public Money evaluate() {
        Money leftMoney = left.evaluate();
        Money rightMoney = right.evaluate();
        if (operator == TokenType.PLUS) {
            return leftMoney.plus(rightMoney);
        } else {
            return leftMoney.minus(rightMoney);
        }
    }
}
