package com.FinCalc;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String input){
        StringBuilder sb = new StringBuilder();
        List<Token> t = new ArrayList<>();
        for(int i = 0; i < input.length(); i++){
            if(Character.isLetter(input.charAt(i))){
                sb.append(input.charAt(i));
            } else if (Character.isDigit(input.charAt(i))) {
                sb.append(input.charAt(i));
            } else if (input.charAt(i)=='+') {
                t.add(new Token("+",TokenType.PLUS));
            } else if (input.charAt(i)=='-') {
                t.add(new Token("-",TokenType.MINUS));
            } else if (input.charAt(i)=='(') {
                t.add(new Token("(",TokenType.LPAREN));
            } else if (input.charAt(i)==')') {
                t.add(new Token(")",TokenType.RPAREN));
            }
        }
    }
}
