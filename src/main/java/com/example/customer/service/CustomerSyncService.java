package com.example.customer.service;

import com.example.customer.AuthFeign.ApiService;
import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSyncService {
    private ApiService apiService;
    private CustomerRepository customerRepository;
    private CustomerUpdatingService customerUpdatingService;

    public CustomerSyncService(ApiService apiService,
                               CustomerRepository customerRepository,
                               CustomerUpdatingService customerUpdatingService) {
        this.apiService = apiService;
        this.customerRepository = customerRepository;
        this.customerUpdatingService = customerUpdatingService;
    }

    public void synchronizeWithDatabase(){
        List<Customer> customerList = apiService.authenticateAndGetCustomerList();
        for(Customer customer: customerList){
            Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
            if(!existingCustomer.isPresent()){
                customerRepository.save(customer);
            }
            else {
                Customer customer1 = existingCustomer.get();
                customerUpdatingService.updateCustomer(customer1, customer);
                customerRepository.save(customer1);
            }
        }
    }
}
