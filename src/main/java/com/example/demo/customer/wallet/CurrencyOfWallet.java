package com.example.demo.customer.wallet;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyOfWallet {

    private String nameCurrency;
    private Double quantity;

   /* public Map<String, Double> kindOfCurrency(*//*String nameCurrency, Double quantity*//*){
        Map<String, Double> currency = null;
        currency.put(this.nameCurrency, this.quantity);
        return currency;
    }*/
}
