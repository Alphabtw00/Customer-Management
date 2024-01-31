package com.example.customer.controller;
import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerSearchService;
import com.example.customer.service.CustomerSyncService;
import com.example.customer.service.CustomerUpdatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerListController {
    private CustomerRepository customerRepository;
    private CustomerUpdatingService customerUpdatingService;
    private CustomerSyncService customerSyncService;
    private CustomerSearchService customerSearchService;

    @Autowired
    public CustomerListController(CustomerRepository customerRepository,
                                  CustomerUpdatingService customerUpdatingService,
                                  CustomerSyncService customerSyncService,
                                  CustomerSearchService customerSearchService) {
        this.customerRepository = customerRepository;
        this.customerUpdatingService = customerUpdatingService;
        this.customerSyncService = customerSyncService;
        this.customerSearchService = customerSearchService;
    }



    @GetMapping
    public String getList(Model model) {
        return "customerList";
    }


    @GetMapping("edit/{customerId}")
    public String getEditForm(@PathVariable String customerId, Model model){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            model.addAttribute("currentCustomer", optionalCustomer.get());
            model.addAttribute("newCustomer", new Customer());
            return "editCustomer";
        }
        else {
            return "customerList";
        }
    }

    @PostMapping("/edit/{customerId}")
    public String updateCustomer(@ModelAttribute("newCustomer") Customer newCustomer,
                                 @PathVariable String customerId){
        Optional<Customer> optionalOriginalCustomer = customerRepository.findById(customerId);
        if (optionalOriginalCustomer.isPresent()){
            Customer originalCustomer = optionalOriginalCustomer.get();
            customerUpdatingService.updateCustomer(originalCustomer, newCustomer);
            customerRepository.save(originalCustomer);
            return "redirect:/customer";
        }
        else {
            return "redirect:/customer";
        }
    }

    @PostMapping ("/delete/{customerId}")
    public String deleteCustomer(@PathVariable String customerId){
        customerRepository.deleteById(customerId);
        return "redirect:/customer";
    }

    @GetMapping("/add")
    public String getAddCustomer(Model model){
        model.addAttribute("addCustomer", new Customer());
        return "addCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("addCustomer") Customer customer){
        customer.setId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return "redirect:/customer";
    }

    @PostMapping("/sync")
    public String syncCustomers(){
        customerSyncService.synchronizeWithDatabase();
        return "redirect:/customer";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam(name = "parameter", required = false, defaultValue = "firstName")
                                     String parameter,
                                 @RequestParam(value = "keyword", required = true) String keyword,
                                 Model model, @ModelAttribute("customerList") List<Customer> customerList){

        List<Customer> filteredList = customerSearchService.performSearch(customerList, parameter, keyword);
        model.addAttribute("filteredList", filteredList);
        return "customerList";
    }

    @PostMapping("/customer/clearSearch")
    public String clearSearch(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("filteredList", null);
        return "redirect:/customer";
    }

    @ModelAttribute
    public void customerList(Model model){
        List<Customer> customerList = customerSearchService.getAllCustomers();
        model.addAttribute("customerList", customerList);
    }
}
