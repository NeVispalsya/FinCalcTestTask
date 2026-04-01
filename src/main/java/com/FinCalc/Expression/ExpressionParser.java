package com.FinCalc.Expression;
import com.FinCalc.Money.Currency;
import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.Token;
import com.FinCalc.Tokenization.TokenType;

import java.math.BigDecimal;
import java.util.List;

public class ExpressionParser {
    public Expression parse(List<Token> tokens){

       for(Token token : tokens){
           if(token.getType()==TokenType.PLUS||token.getType()==TokenType.MINUS){
               parse(tokens);
           }
           if(token.getType()==TokenType.NUMBER){
               String s = token.getValue().replaceAll("[^0-9.]", "");
               if(token.getValue().charAt(0)=='$'){
                   BigDecimal bd = new BigDecimal(s);
                   MoneyExpression m = new MoneyExpression(new Money(bd, Currency.$));
               }else if(token.getValue().toLowerCase().endsWith("eur")){
                   BigDecimal bd = new BigDecimal(s);
                   MoneyExpression m = new MoneyExpression(new Money(bd, Currency.eur));
               }
           }

       }
       return null;
    }
}
