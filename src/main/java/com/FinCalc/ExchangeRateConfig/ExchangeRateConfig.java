package com.FinCalc.ExchangeRateConfig;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

public class ExchangeRateConfig {
    private final Properties properties = new Properties();

    public ExchangeRateConfig() {
        try (InputStream is = getClass().
                getClassLoader().
                getResourceAsStream("exchangeRate.properties")) {
            if (is == null) {
                throw new RuntimeException("Файл exchangeRate.properties не найден");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении пропертис", e);
        }
    }

    public BigDecimal getRate(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Курс для '" + key + "' не найден");
        }
        return new BigDecimal(value);
    }

    public BigDecimal getUsdToEur() {
        return getRate("usd.to.eur");
    }

    public BigDecimal getEurToUsd() {
        return getRate("eur.to.usd");
    }
}
