package com.example.demo.customer.wallet;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyOfWallet {

//    @SequenceGenerator(
//            name = "wallet_sequence",
//            sequenceName = "wallet_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "wallet_sequence"
//    )
//    @Id
//    private Long id;
    private String nameCurrency;
    private Double quantity;


//    @Enumerated(EnumType.STRING)
//    private Map<String, Double> quantityOfCurrency;


    public Map<String, Double> kindOfCurrency(/*String nameCurrency, Double quantity*/){
        Map<String, Double> currency = null;
        currency.put(this.nameCurrency, this.quantity);
        return currency;
    }
}
