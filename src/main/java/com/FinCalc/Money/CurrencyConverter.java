package com.FinCalc.Money;
import com.FinCalc.Exception.InvalidMoneyOperationException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {
    BigDecimal eurToUsdRate = new BigDecimal("1.2");
    BigDecimal usdToEurRate = new BigDecimal("0.8");
    public Money toDollar(Money money){
        if(money.getCurrency()== Currency.$){
            throw new InvalidMoneyOperationException("You cannot convert currencies of the same type");
        }else{
            return new Money(money.getAmount().multiply(eurToUsdRate).setScale(2,RoundingMode.HALF_UP),Currency.$);
        }
    }
    public Money toEuro(Money money){
        if(money.getCurrency()==Currency.eur){
            throw new InvalidMoneyOperationException("You cannot convert currencies of the same type");
        }else{
            return new Money(money.getAmount().multiply(usdToEurRate).setScale(2, RoundingMode.HALF_UP),Currency.eur);
        }
    }
}
