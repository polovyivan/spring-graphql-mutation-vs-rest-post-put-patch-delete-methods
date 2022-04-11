package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.UpdateCustomerRequest;
import com.polovyi.ivan.service.CustomerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public record CustomerGraphQLMutationController(CustomerService customerService) implements GraphQLMutationResolver {

    public String createCustomer(@Valid CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    public String updateCustomer(@NotNull String customerId,
            @Valid UpdateCustomerRequest updateCustomerRequest) {
        customerService.updateCustomer(customerId, updateCustomerRequest);
        return customerId;
    }

}
