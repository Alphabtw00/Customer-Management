package com.example.customer.service;

import com.example.customer.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdatingService {

    public void updateCustomer(Customer oldCustomer, Customer newCustomer) {
        if (newCustomer.getFirstName() != null && !newCustomer.getFirstName().isEmpty()) {
            oldCustomer.setFirstName(newCustomer.getFirstName());
        }

        if (newCustomer.getLastName() != null && !newCustomer.getLastName().isEmpty()) {
            oldCustomer.setLastName(newCustomer.getLastName());
        }

        if (newCustomer.getStreet() != null && !newCustomer.getStreet().isEmpty()) {
            oldCustomer.setStreet(newCustomer.getStreet());
        }

        if (newCustomer.getAddress() != null && !newCustomer.getAddress().isEmpty()) {
            oldCustomer.setAddress(newCustomer.getAddress());
        }

        if (newCustomer.getCity() != null && !newCustomer.getCity().isEmpty()) {
            oldCustomer.setCity(newCustomer.getCity());
        }

        if (newCustomer.getState() != null && !newCustomer.getState().isEmpty()) {
            oldCustomer.setState(newCustomer.getState());
        }

        if (newCustomer.getEmail() != null && !newCustomer.getEmail().isEmpty()) {
            oldCustomer.setEmail(newCustomer.getEmail());
        }

        if (newCustomer.getPhone() != null && !newCustomer.getPhone().isEmpty()) {
            oldCustomer.setPhone(newCustomer.getPhone());
        }

    }
}

