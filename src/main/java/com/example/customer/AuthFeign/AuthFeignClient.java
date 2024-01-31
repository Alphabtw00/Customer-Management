package com.example.customer.AuthFeign;

import com.example.customer.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "auth-client", url = "https://qa.sunbasedata.com/sunbase/portal/api")
public interface AuthFeignClient {

    @PostMapping(value = "/assignment_auth.jsp", consumes = MediaType.APPLICATION_JSON_VALUE)
    String authenticateUser(@RequestBody AuthRequest authRequest);


    @GetMapping(value = "/assignment.jsp")
    List<Customer> getCustomerList(@RequestParam("cmd") String command,
                                   @RequestHeader("Authorization") String authorization);
}