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

    @PostMapping(path = "/edit/{nameCurrency}")
    public List<Currency> updateCurrency(
            @PathVariable(name = "nameCurrency") String nameCurrency,
            @RequestParam("value") Double value){
        return List.of(
                currencyService.updateValueCurrency(nameCurrency, value));
    }


    @GetMapping(path = "{nameCurrency}")
    public List<Currency> getCurrency(
            @PathVariable(name = "nameCurrency") String nameCurrency,
            @RequestParam("baseCurrency") String baseCurrency
            ){
        if (!baseCurrency.isEmpty())
            currencyService.countValueCurrency(nameCurrency, baseCurrency);

        return currencyService.findCurrency(nameCurrency);
    }

    @DeleteMapping("/delete/{id}")
    public List<Currency> deleteCurrency(@PathVariable("id") Long id){
        return currencyService.deleteCurrency(id);
    }

}
