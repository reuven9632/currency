package com.example.demo.currency;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                        .NameCurrency(currency.getNameCurrency())
                        .AmountOfCurrency(currency.getAmountOfCurrency())
                        .ValueOfCurrency(currency.getValueOfCurrency())
                        .BaseCurrency(currency.getBaseCurrency())
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
    public Currency setBaseCurrency(String nameCurrency, String baseCurrency){
        Currency currencyDB = currencyRepo.findByNameCurrency(nameCurrency)
                                        .orElseThrow(
                                                () -> new IllegalStateException(String.format(NOT_FOUND_CURRENCY_MESSAGE, nameCurrency))
                                        );
        currencyDB.setBaseCurrency(baseCurrency);
//        currencyRepo.save(currencyDB);
        return currencyDB;
    }

    /**
     * this method uses setBaseCurrency to calculate the change in the base currency,
     * and then calculates the value of that currency relative to the changed argument.
     * On output returns the converted currency*/
    public Currency countValueCurrency(String nameCurrency, String baseCurrency){
        Currency currencyDB = setBaseCurrency(nameCurrency, baseCurrency);
        currencyDB.setValueOfCurrency(
                currencyDB.getValueOfCurrency() *
                        currencyRepo.findByNameCurrency(baseCurrency)
                                .orElseThrow(
                                        () -> new IllegalStateException(String.format(NOT_FOUND_CURRENCY_MESSAGE, nameCurrency))
                                )
                                .getValueOfCurrency()
        );
//        currencyRepo.save(currencyDB);
        return currencyDB;

    }

    public Currency updateValueCurrency(String nameCurrency, Double value){
        Currency currencyDB = currencyRepo.findByNameCurrency(nameCurrency)
                .orElseThrow(
                        () -> new IllegalStateException(String.format(NOT_FOUND_CURRENCY_MESSAGE, nameCurrency))
                );

        currencyDB.setValueOfCurrency(value);
        currencyRepo.save(currencyDB);
        return currencyDB;

    }

    public List<Currency> findCurrency(String nameCurrency) {
        return List.of(
                currencyRepo.findByNameCurrency(nameCurrency)
                        .orElseThrow(
                                () -> new IllegalStateException(String.format(NOT_FOUND_CURRENCY_MESSAGE, nameCurrency))
                        )
        );
    }
}
