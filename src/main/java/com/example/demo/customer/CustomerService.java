package com.example.demo.customer;

import com.example.demo.currency.CurrencyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CurrencyRepo currencyRepo;
    private final CustomerRepo customerRepo;

    public Customer addMoneyToCustomer(String nameCustomer, WalletRequest walletRequest){
        Customer customer = customerRepo.findByName(nameCustomer).orElseThrow(
                () -> new IllegalStateException(String.format("Not found Customer - %s", nameCustomer)));

        Map<String, Double> quantityOfCurrency = addMoneyToWallet(walletRequest);
        customer.setQuantityOfCurrency(quantityOfCurrency);
        customerRepo.save(customer);
        return customer;
    }



    public Map<String, Double> addMoneyToWallet(WalletRequest walletRequest){
        if (!validateWalletRequest(walletRequest))
            throw new IllegalStateException("Currency is not valid //WalletService.addMoneyToWallet");
        Map<String, Double> quantityOfCurrency = null;
        quantityOfCurrency.put(walletRequest.getNameCurrency(), walletRequest.getQuantity());
        return quantityOfCurrency;
    }


    protected Boolean validateWalletRequest(WalletRequest walletRequest){
        boolean present = currencyRepo.findByNameCurrency(
                walletRequest.getNameCurrency()).isPresent();
        return present;
    }

}
