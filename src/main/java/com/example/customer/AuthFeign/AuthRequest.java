package com.example.customer.AuthFeign;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class AuthRequest {

    private String login_id;
    private String password;

}