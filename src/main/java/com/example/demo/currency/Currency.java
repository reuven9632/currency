package com.example.demo.currency;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCurrency;
    private Double amountOfCurrency;  //колво валюты
    private Double valueOfCurrency;   //стоимость валюты
    private String baseCurrency = "USD";
//    private Currency baseCurrency;

    public Currency(CurrencyBuilder builder) {
        this.nameCurrency = builder.nameCurrency;
        this.amountOfCurrency = builder.amountOfCurrency;
        this.valueOfCurrency = builder.valueOfCurrency;
        this.baseCurrency = builder.baseCurrency;
    }

    public static CurrencyBuilder builder(){
        return new CurrencyBuilder();
    }




    @NoArgsConstructor
    public static class CurrencyBuilder {

        private String nameCurrency;
        private Double amountOfCurrency;  //колво валюты
        private Double valueOfCurrency;   //стоимость валюты
//        private Currency baseCurrency;
        private String baseCurrency;

        public Currency build(){
            return new Currency(this);
        }

        public CurrencyBuilder setNameCurrency(String nameCurrency) {
            this.nameCurrency = nameCurrency;
            return this;
        }

        public CurrencyBuilder setAmountOfCurrency(Double amountOfCurrency) {
            this.amountOfCurrency = amountOfCurrency;
            return this;
        }

        public CurrencyBuilder setValueOfCurrency(Double valueOfCurrency) {
            this.valueOfCurrency = valueOfCurrency;
            return this;
        }

        public CurrencyBuilder setBaseCurrency(String baseCurrency) {
            this.baseCurrency = baseCurrency;
            return this;

        }
    }
}