package com.FinCalc;

import com.FinCalc.Expression.Expression;
import com.FinCalc.Expression.ExpressionParser;
import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.Token;
import com.FinCalc.Tokenization.Tokenizer;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean calc = true;
        do{
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            Tokenizer tokenizer = new Tokenizer();

            List<Token> tokens = tokenizer.tokenize(input);

            ExpressionParser parser = new ExpressionParser();

            Expression expression = parser.parse(tokens);

            Money result = expression.evaluate();

            System.out.println(result);

            String again = scanner.nextLine();

            if (again.equals("f")){
                calc=false;
            }
        }while (calc);
    }
}