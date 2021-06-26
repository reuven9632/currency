package com.example.demo.customer;

import com.example.demo.currency.CurrencyRepo;
import com.example.demo.customer.wallet.CurrencyOfWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

/*

//        ArrayList<CurrencyOfWallet> wallet = new ArrayList<>();
        try {
//            wallet.add(currencyOfWallet);
            customer.setWallet(currencyOfWallet);
            customerRepo.save(customer);
        }catch (NullPointerException e) {
            throw new IllegalStateException("Not save currencyOfWallet to Wallet");
        }
*/




        return customer;
    }




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

    public Customer findCustomerById(Long id) {
        return customerRepo.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Not found Customer with id - %s", id))
        );
    }
}
