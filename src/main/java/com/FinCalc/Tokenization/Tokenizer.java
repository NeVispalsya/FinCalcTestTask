package com.FinCalc.Tokenization;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public void addTokenFromBuilder(StringBuilder sb,List<Token> tokens){
        if (!sb.isEmpty()) {
            if(Character.isLetter(sb.charAt(0))){
                tokens.add(new Token(sb.toString(), TokenType.FUNCTION));
                sb.setLength(0);
            }else{
                tokens.add(new Token(sb.toString(),TokenType.NUMBER));
                sb.setLength(0);
            }
        }
    }
    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c) || c == '$' || c == '.') {
                sb.append(c);
                continue;
            }
            addTokenFromBuilder(sb,tokens);
            if (c == '+') {
                tokens.add(new Token("+", TokenType.PLUS));
            } else if (c == '-') {
                tokens.add(new Token("-", TokenType.MINUS));
            } else if (c == '(') {
                tokens.add(new Token("(", TokenType.LPAREN));
            } else if (c == ')') {
                tokens.add(new Token(")", TokenType.RPAREN));
            } else if (c == ' ') {
                continue;
            }
        }
        addTokenFromBuilder(sb,tokens);
        return tokens;
    }

}
