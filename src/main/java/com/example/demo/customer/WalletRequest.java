package com.example.demo.customer;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {

    private Double quantity;
    private String nameCurrency;

}
