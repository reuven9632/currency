package com.example.demo.customer.wallet;

import com.example.demo.customer.Customer;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyOfWallet {

    @SequenceGenerator(
            name = "currencyOfWallet_sequence",
            sequenceName = "currencyOfWallet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currencyOfWallet_sequence"
    )
    @Id
    private Long id;
    private String nameCurrency;
    private Double quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "CurrencyOfWallet{" +
                "id=" + id +
                ", nameCurrency='" + nameCurrency + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
