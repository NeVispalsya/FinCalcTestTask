package com.FinCalc.Expression;
import com.FinCalc.Exception.InvalidMoneyOperationException;
import com.FinCalc.Money.Currency;
import com.FinCalc.Money.CurrencyConverter;
import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.Token;
import com.FinCalc.Tokenization.TokenType;
import java.math.BigDecimal;
import java.util.List;

public class ExpressionParser {
    private CurrencyConverter converter = new CurrencyConverter();

    public Expression parse(List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Empty expression");
        }
        if (tokens.get(0).getType() == TokenType.FUNCTION) {
            String functionName = tokens.get(0).getValue(); // toDollar или toEuro
            if (tokens.size() < 4 || tokens.get(1).getType() != TokenType.LPAREN) {
                throw new InvalidMoneyOperationException("Invalid function syntax");
            }
            int depth = 0;
            int endIndex = -1;
            for (int i = 1; i < tokens.size(); i++) {
                Token t = tokens.get(i);
                if (t.getType() == TokenType.LPAREN)
                    depth++;
                else if (t.getType() == TokenType.RPAREN)
                    depth--;
                if (depth == 0) {
                    endIndex = i;
                    break;
                }
            }
            if (endIndex == -1)
                throw new InvalidMoneyOperationException("Unmatched parentheses in function");
            List<Token> argumentTokens = tokens.subList(2, endIndex);
            Expression argumentExpr = parse(argumentTokens);
            FunctionExpression funcExpr = new FunctionExpression(functionName, argumentExpr, converter);
            if (endIndex < tokens.size() - 1) {
                List<Token> rest = tokens.subList(endIndex + 1, tokens.size());
                Expression rightExpr = parse(rest);
                return new OperationExpression(funcExpr, rightExpr, rest.get(0).getType(), converter);
            } else {
                return funcExpr;
            }
        }
        if (tokens.get(0).getType() == TokenType.LPAREN) {
            int depth = 0;
            boolean wrapped = true;
            for (int i = 0; i < tokens.size(); i++) {
                Token t = tokens.get(i);
                if (t.getType() == TokenType.LPAREN)
                    depth++;
                else if (t.getType() == TokenType.RPAREN)
                    depth--;
                if (depth == 0 && i < tokens.size() - 1)
                    wrapped = false;
            }
            if (wrapped)
                return parse(tokens.subList(1, tokens.size() - 1));
        }
        if (tokens.size() == 1) {
            Token token = tokens.get(0);
            String value = token.getValue();
            String s = value.replaceAll("[^0-9.\\-]", "");
            if (value.startsWith("$"))
                return new MoneyExpression(new Money(new BigDecimal(s), Currency.$));
            else if (value.toLowerCase().endsWith("eur"))
                return new MoneyExpression(new Money(new BigDecimal(s), Currency.eur));
            else
                throw new InvalidMoneyOperationException("Invalid money value: " + value);
        }
        return toOperationExpression(tokens);
    }
    private Expression toOperationExpression(List<Token> tokens) {
        int depth = 0;
        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token token = tokens.get(i);
            if (token.getType() == TokenType.RPAREN) depth++;
            else if (token.getType() == TokenType.LPAREN) depth--;
            if (depth == 0 && (token.getType() == TokenType.PLUS || token.getType() == TokenType.MINUS)) {
                List<Token> left = tokens.subList(0, i);
                List<Token> right = tokens.subList(i + 1, tokens.size());
                if (left.isEmpty() || right.isEmpty())
                    throw new InvalidMoneyOperationException("Invalid expression");
                Expression leftExpr = parse(left);
                Expression rightExpr = parse(right);
                return new OperationExpression(leftExpr, rightExpr, token.getType(), converter);
            }
        }
        throw new InvalidMoneyOperationException("Cannot parse operation");
    }
}
