package com.example.demo.customer;

import com.example.demo.customer.wallet.CurrencyOfWallet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public List<Customer> addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return customerService.findAllCustomers();
    }

    @PostMapping(path = "/{nameCustomer}/money")
    public Customer addMoney(
            @PathVariable("nameCustomer") String nameCustomer,
            @RequestBody CurrencyOfWallet currencyOfWallet){

        return customerService.addMoneyToCustomer(nameCustomer, currencyOfWallet);
    }

    @DeleteMapping(path = "delete/{id}")
    public List<Customer> deleteCustomer(@PathVariable("id") Long id){
        return customerService.deleteCustomer(id);
    }



}
