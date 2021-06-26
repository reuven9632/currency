package com.example.demo.customer;

//import com.example.demo.customer.wallet.CurrencyOfWallet;
import com.example.demo.customer.wallet.CurrencyOfWallet;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
//    private Map<String, Double> quantityOfCurrency;

//    @OneToMany(targetEntity = CurrencyOfWallet.class)
//    @ElementCollection(targetClass = CurrencyOfWallet.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "customer_wallet", joinColumns = @JoinColumn(name = "customer_id"))
//    private CurrencyOfWallet wallet;


    private ArrayList<CurrencyOfWallet> wallet = new ArrayList<CurrencyOfWallet>();

    public void setWallet(CurrencyOfWallet wallet) {
        this.wallet.add(wallet);
    }
}
