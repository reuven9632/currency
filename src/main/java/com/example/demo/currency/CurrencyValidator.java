package com.example.demo.currency;

import java.util.function.Function;

import static com.example.demo.currency.CurrencyValidator.CurrencyResult.*;

public interface CurrencyValidator extends Function<Currency, CurrencyValidator.CurrencyResult> {

    static CurrencyValidator isNameValid(){
        return currency -> currency.getNameCurrency().length() > 2 ? SUCCESS : NAME_CURRENCY_NOT_VALID;
    }

    static CurrencyValidator isAmountOfCurrencyValid(){
        return currency -> currency.getAmountOfCurrency() > 0 ? SUCCESS : AMOUNT_OF_CURRENCY_NOT_VALID;
    }

    static CurrencyValidator isValueOfCurrencyValid(){
        return currency -> currency.getValueOfCurrency() > 0 ? SUCCESS : VALUE_OF_CURRENCY_NOT_VALID;
    }

    static CurrencyValidator and(CurrencyValidator other){
        return currency -> {
            CurrencyResult result = this.apply(currency);
            return result.equals(SUCCESS) ? other.apply() : result;
        };
    }



    enum CurrencyResult{
        SUCCESS,
        NAME_CURRENCY_NOT_VALID,
        AMOUNT_OF_CURRENCY_NOT_VALID,
        VALUE_OF_CURRENCY_NOT_VALID
    }

}
