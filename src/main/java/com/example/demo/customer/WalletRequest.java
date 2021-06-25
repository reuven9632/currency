package com.example.demo.customer;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {

    private String nameCurrency;
    private Double quantity;

}
