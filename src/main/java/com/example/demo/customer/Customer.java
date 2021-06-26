package com.example.demo.customer;

//import com.example.demo.customer.wallet.CurrencyOfWallet;

import com.example.demo.customer.wallet.CurrencyOfWallet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<CurrencyOfWallet> wallet;

    public void addCurrencyToWallet(CurrencyOfWallet currency){
        if (wallet == null)
            wallet = new ArrayList<>();
        wallet.add(currency);
        currency.setCustomer(this);    //fot Bi Directional binding
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
