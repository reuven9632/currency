package com.example.demo.currency;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {



    @SequenceGenerator(
            name = "currency_sequence",
            sequenceName = "currency_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_sequence")
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameCurrency;
    private Double amountOfCurrency;  //колво валюты
    private Double valueOfCurrency;   //стоимость валюты
    private String baseCurrency = "USD";

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
        private String baseCurrency = "USD";

        public Currency build(){
            return new Currency(this);
        }

        public CurrencyBuilder NameCurrency(String nameCurrency) {
            this.nameCurrency = nameCurrency;
            return this;
        }

        public CurrencyBuilder AmountOfCurrency(Double amountOfCurrency) {
            this.amountOfCurrency = amountOfCurrency;
            return this;
        }

        public CurrencyBuilder ValueOfCurrency(Double valueOfCurrency) {
            this.valueOfCurrency = valueOfCurrency;
            return this;
        }

        public CurrencyBuilder BaseCurrency(String baseCurrency) {
            if (!(baseCurrency == null))
                this.baseCurrency = baseCurrency;
            return this;

        }
    }
}
