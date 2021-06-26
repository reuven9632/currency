package com.example.demo.customer;

import com.example.demo.currency.CurrencyRepo;
import com.example.demo.customer.wallet.CurrencyOfWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CurrencyRepo currencyRepo;
    private final CustomerRepo customerRepo;

    public Customer addMoneyToCustomer(String nameCustomer, CurrencyOfWallet currencyOfWallet){
        if (!validateCurrencyOfWallet(currencyOfWallet))
            throw new IllegalStateException("Currency is not valid //WalletService.addMoneyToWallet");

        Customer customer = customerRepo.findByName(nameCustomer).orElseThrow(
                () -> new IllegalStateException(String.format("Not found Customer - %s", nameCustomer)));

//        Map<String, Double> quantityOfCurrency = addMoneyToWallet(walletRequest);
//        customer.setQuantityOfCurrency(quantityOfCurrency);
        ArrayList<CurrencyOfWallet> wallet = customer.getWallet();
        wallet.add(currencyOfWallet);
        customer.setWallet(wallet);
        customerRepo.save(customer);
        return customer;
    }



//    protected CurrencyOfWallet addMoneyToWallet(CurrencyOfWallet currencyOfWallet){
//
//        return quantityOfCurrency;
//    }


    protected Boolean validateCurrencyOfWallet(CurrencyOfWallet currencyOfWallet){
        boolean present = currencyRepo.findByNameCurrency(
                currencyOfWallet.getNameCurrency()).isPresent();
        return present;
    }

    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    public List<Customer> deleteCustomer(Long id) {
        Customer customerDB = customerRepo.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Not found Customer with id - %s", id))
        );
        customerRepo.delete(customerDB);
        return findAllCustomers();
    }
}
