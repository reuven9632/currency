package com.example.demo.currency;

import lombok.AllArgsConstructor;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRepo currencyRepo;
    private final String NOT_FOUND_CURRENCY_MESSAGE = "Not found %s currency";

    public List<Currency> BuildCurrency(Currency currency){

        boolean currencyIsPresent = currencyRepo.findByNameCurrency(currency.getNameCurrency()).isPresent();
        if (currencyIsPresent)
            throw new IllegalStateException("Error: it is impossible to mine the currency, as it has already been added");

        currencyRepo.save(
                Currency.builder()
                        .setNameCurrency(currency.getNameCurrency())
                        .setAmountOfCurrency(currency.getAmountOfCurrency())
                        .setValueOfCurrency(currency.getValueOfCurrency())
                        .setBaseCurrency(currency.getBaseCurrency())
                        .build()
        );

        return currencyRepo.findAll();
    }

    /**Method to change baseCurrency
     * example: if calculations are needed not in relation to the standard currency,
     * which I mean the dollar.
     * I substitute the currency that needs to be changed in the first parameter,
     * the second parameter to which argument to change
     * */
    public Currency setBaseCurrency(Currency currency, String baseCurrency){
        Currency currencyDB = currencyRepo.findByNameCurrency(currency.getNameCurrency())
                                        .orElseThrow(
                                                () -> new IllegalStateException(String.format(NOT_FOUND_CURRENCY_MESSAGE, currency.getNameCurrency()))
                                        );
        currencyDB.setBaseCurrency(baseCurrency);
        currencyRepo.save(currencyDB);
        return currencyDB;
    }

    /**
     * this method uses setBaseCurrency to calculate the change in the base currency,
     * and then calculates the value of that currency relative to the changed argument.
     * On output returns the converted currency*/
    public Currency updateValueCurrency(Currency currency, String baseCurrency){
        Currency currencyDB = setBaseCurrency(currency, baseCurrency);
        currencyDB.setValueOfCurrency(
                currencyDB.getValueOfCurrency() *
                        currencyRepo.findByNameCurrency(baseCurrency)
                                .orElseThrow(
                                        () -> new IllegalStateException(String.format(NOT_FOUND_CURRENCY_MESSAGE, currency.getNameCurrency()))
                                )
                                .getValueOfCurrency()
        );
        currencyRepo.save(currencyDB);
        return currencyDB;

    }

}
