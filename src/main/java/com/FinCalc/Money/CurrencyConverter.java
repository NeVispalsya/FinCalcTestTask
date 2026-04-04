package com.FinCalc.Money;
import com.FinCalc.Exception.InvalidMoneyOperationException;
import com.FinCalc.ExchangeRateConfig.ExchangeRateConfig;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {
    private ExchangeRateConfig exRatConf = new ExchangeRateConfig();
    public Money toDollar(Money money){
        if(money.getCurrency()== Currency.$){
            throw new InvalidMoneyOperationException("You cannot convert currencies of the same type");
        }else{
            return new Money(money.getAmount().multiply(exRatConf.getEurToUsd()).setScale(2,RoundingMode.HALF_UP),Currency.$);
        }
    }
    public Money toEuro(Money money){
        if(money.getCurrency()==Currency.eur){
            throw new InvalidMoneyOperationException("You cannot convert currencies of the same type");
        }else{
            return new Money(money.getAmount().multiply(exRatConf.getUsdToEur()).setScale(2, RoundingMode.HALF_UP),Currency.eur);
        }
    }
}
