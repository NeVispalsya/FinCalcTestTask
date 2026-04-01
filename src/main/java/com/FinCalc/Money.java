package com.FinCalc;

import java.math.BigDecimal;

public class Money {
    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(2);
        this.currency = currency;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String toStringToEuro() {
        return ""+amount+currency;
    }

    public String toStringToDollar() {
        return ""+currency+amount ;
    }

    public Money plus(Money money){
        if(money.getCurrency()==this.currency){
            return new Money(getAmount().add(money.getAmount()),currency);
        }else{
            throw new InvalidMoneyOperationException("Cannot add "+money.getCurrency()+"to"+getCurrency());
        }
    }
    public Money minus(Money money){
        if(money.getCurrency()==this.currency){
            return new Money(getAmount().subtract(money.getAmount()),currency);
        }else{
            throw new InvalidMoneyOperationException("Cannot subtract "+money.getCurrency()+" from "+getCurrency());
        }
    }
}
