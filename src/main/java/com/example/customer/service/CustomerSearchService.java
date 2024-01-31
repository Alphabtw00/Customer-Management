package com.example.customer.service;


import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerSearchService {
    private CustomerRepository customerRepository;

    public CustomerSearchService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        Iterable<Customer> tempList = customerRepository.findAll();
        List<Customer> customerList = new ArrayList<>();
        tempList.forEach(customerList::add);
        return customerList;
    }


    public List<Customer> performSearch(List<Customer> customerList, String parameter, String keyword) {
        List<Customer> filteredList = new ArrayList<>();

        if (parameter != null && keyword != null) {
            for (Customer customer : customerList) {
                String lowerCaseKeyword = keyword.toLowerCase(); // Convert keyword to lowercase for case-insensitive comparison

                switch (parameter) {
                    case "firstName":
                        if (containsIgnoreCase(customer.getFirstName(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "lastName":
                        if (containsIgnoreCase(customer.getLastName(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "street":
                        if (containsIgnoreCase(customer.getStreet(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "address":
                        if (containsIgnoreCase(customer.getAddress(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "city":
                        if (containsIgnoreCase(customer.getCity(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "state":
                        if (containsIgnoreCase(customer.getState(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "email":
                        if (containsIgnoreCase(customer.getEmail(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                    case "phone":
                        if (containsIgnoreCase(customer.getPhone(), lowerCaseKeyword)) {
                            filteredList.add(customer);
                        }
                        break;
                }
            }
        }

        return filteredList;
    }

    public boolean containsIgnoreCase(String source, String target) {
        return source != null && source.toLowerCase().contains(target);
    }

}
