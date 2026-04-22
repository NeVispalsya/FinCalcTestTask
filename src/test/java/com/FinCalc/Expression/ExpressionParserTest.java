package com.FinCalc.Expression;

import com.FinCalc.Money.Money;
import com.FinCalc.Tokenization.Token;
import com.FinCalc.Tokenization.Tokenizer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.FinCalc.Money.Currency.$;
import static com.FinCalc.Money.Currency.eur;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    Tokenizer tokenizer = new Tokenizer();
    ExpressionParser parser = new ExpressionParser();

    @Test
    void parseToEuro() {
        String input = "toEuro($5+$5)";
        List<Token> tokens = tokenizer.tokenize(input);
        Expression expression = parser.parse(tokens);
        Money result = expression.evaluate();
        String s = "8.00";
        assertEquals(new Money(new BigDecimal(s),eur),result);
    }

    @Test
    void parseToDollar() {
        String input = "toDollar(5eur+5eur)";
        List<Token> tokens = tokenizer.tokenize(input);
        Expression expression = parser.parse(tokens);
        Money result = expression.evaluate();
        String s = "15.00";
        assertEquals(new Money(new BigDecimal(s),$),result);
    }
    @Test
    void parseDollarPusDollar() {
        String input = "$5eur+$5";
        List<Token> tokens = tokenizer.tokenize(input);
        Expression expression = parser.parse(tokens);
        Money result = expression.evaluate();
        String s = "10.00";
        assertEquals(new Money(new BigDecimal(s),$),result);
    }
    @Test
    void parseEuroPlusEuro() {
        String input = "5eur+5eur";
        List<Token> tokens = tokenizer.tokenize(input);
        Expression expression = parser.parse(tokens);
        Money result = expression.evaluate();
        String s = "10.00";
        assertEquals(new Money(new BigDecimal(s),eur),result);
    }
    @Test
    void difficultParseToDollar() {
        String input = "toDollar(toEuro($5+$5))";
        List<Token> tokens = tokenizer.tokenize(input);
        Expression expression = parser.parse(tokens);
        Money result = expression.evaluate();
        String s = "12.00";
        assertEquals(new Money(new BigDecimal(s),$),result);
    }
    @Test
    void difficultParseToEuro() {
        String input = "toEuro(toDollar(5eur+5eur))";
        List<Token> tokens = tokenizer.tokenize(input);
        Expression expression = parser.parse(tokens);
        Money result = expression.evaluate();
        String s = "12.00";
        assertEquals(new Money(new BigDecimal(s),eur),result);
    }
}