package com.FinCalc;

import com.FinCalc.Expression.Expression;
import com.FinCalc.Expression.ExpressionParser;
import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.Token;
import com.FinCalc.Tokenization.Tokenizer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String input = "toDollar($10 + 5eur)";
//        Tokenizer tokenizer = new Tokenizer();
//        List<Token> tokens = tokenizer.tokenize(input);
//        for (Token t : tokens) {
//            System.out.println(t.getType() + " : " + t.getValue());
//        }

        String input = "$10 + 5eur";

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(input);

        ExpressionParser parser = new ExpressionParser();
        Expression expression = parser.parse(tokens);

        Money result = expression.evaluate();

        System.out.println(result.getAmount() + " " + result.getCurrency());

    }
}