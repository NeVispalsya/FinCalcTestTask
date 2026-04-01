package com.FinCalc.Expression;

import com.FinCalc.Money.Money;

public class MoneyExpression implements Expression {
    private Money money;

    public MoneyExpression(Money money) {
        this.money = money;
    }

    @Override
    public Money evaluate() {
        return money;
    }
}
