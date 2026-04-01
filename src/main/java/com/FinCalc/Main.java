package com.FinCalc;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "toDollar($10 + 5eur)";
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(input);
        for (Token t : tokens) {
            System.out.println(t.getType() + " : " + t.getValue());
        }
    }
}