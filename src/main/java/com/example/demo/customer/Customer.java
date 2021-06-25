package com.example.demo.customer;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
//    private Wallet wallet;
    private Map<String, Double> quantityOfCurrency;

}
