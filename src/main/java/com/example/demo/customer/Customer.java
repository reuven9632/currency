package com.example.demo.customer;

//import com.example.demo.customer.wallet.CurrencyOfWallet;

import com.example.demo.customer.wallet.CurrencyOfWallet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
//    @OneToMany(cascade = CascadeType.ALL)
//    private CurrencyOfWallet wallet;

}
