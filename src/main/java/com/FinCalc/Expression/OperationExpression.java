package com.FinCalc.Expression;

import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.TokenType;

public class OperationExpression implements Expression {
    private Expression left;
    private Expression right;
    private TokenType operator;

    public OperationExpression(Expression left, Expression right, TokenType operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
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
