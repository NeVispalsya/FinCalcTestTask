package com.FinCalc.Money;
import com.FinCalc.Exception.InvalidMoneyOperationException;
import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(2);
        this.currency = currency;
    }
    public Money(BigDecimal amount) {
        this.amount = amount.setScale(2);
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

    @Override
    public String toString() {
        if(currency == Currency.$){
            return "$" + amount;
        } else {
            return amount + "eur";
        }
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
