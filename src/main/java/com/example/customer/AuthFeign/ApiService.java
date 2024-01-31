package com.example.customer.AuthFeign;

import com.example.customer.entity.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    private AuthFeignClient authFeignClient;
    @Value("${api.loginId}")
    private String loginId;
    @Value("${api.password}")
    private String password;

    public ApiService(AuthFeignClient authFeignClient) {
        this.authFeignClient = authFeignClient;
    }

    public List<Customer> authenticateAndGetCustomerList(){
        AuthRequest authRequest = new AuthRequest(loginId, password);
        String fullResponse = authFeignClient.authenticateUser(authRequest);

        String token = extractTokenFromJsonResponse(fullResponse);

        return authFeignClient.getCustomerList("get_customer_list", "Bearer " + token);
    }

    private static String extractTokenFromJsonResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}