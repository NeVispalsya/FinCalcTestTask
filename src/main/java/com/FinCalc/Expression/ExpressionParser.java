package com.FinCalc.Expression;
import com.FinCalc.Money.Currency;
import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.Token;
import com.FinCalc.Tokenization.TokenType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    public Expression parse(List<Token> tokens){
        if(!tokens.isEmpty()){
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).getType() == TokenType.PLUS || tokens.get(i).getType() == TokenType.MINUS) {
                    List<Token> left = tokens.subList(0, i);
                    List<Token> right = tokens.subList(i + 1, tokens.size());
                    Expression leftExpr = parse(left);
                    Expression rightExpr = parse(right);
                    return new OperationExpression(leftExpr,rightExpr, tokens.get(i).getType());
                }
            }
            String s = tokens.getFirst().getValue().replaceAll("[^0-9.]", "");
            if(tokens.getFirst().getValue().charAt(0)=='$'){
                BigDecimal bd = new BigDecimal(s);
                return new MoneyExpression(new Money(bd, Currency.$));
            }else if(tokens.getFirst().getValue().toLowerCase().endsWith("eur")){
                BigDecimal bd = new BigDecimal(s);
                return new MoneyExpression(new Money(bd, Currency.eur));
            }
        }
        throw new RuntimeException("Invalid expression");
    }
}
