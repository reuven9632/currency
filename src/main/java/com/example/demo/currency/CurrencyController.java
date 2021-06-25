package com.example.demo.currency;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/currency")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping(path = "")
    public List<Currency> addCurrencyToDBWithBuilder(@RequestBody Currency currency){
        return currencyService.BuildCurrency(currency);
    }

}
