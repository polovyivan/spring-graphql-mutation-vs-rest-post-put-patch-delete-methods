package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.service.CustomerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public record CustomerGraphQLController(CustomerService customerService) implements GraphQLMutationResolver {

    public List<CustomerResponse> customers() {
        return customerService.getAllCustomersWithFilters();
    }

    public String createCustomer(@Valid CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }
    //
    //    public List<PurchaseTransactionResponse> customerTransactions(@NotNull String customerId) {
    //        return customerService.getAllCustomerPurchaseTransactions(customerId);
    //    }
    //
    //    public PurchaseTransactionResponse customerTransaction(@NotNull String customerId,
    //            @NotNull String purchaseTransactionId) {
    //        return customerService.getCustomerPurchaseTransactionsById(customerId, purchaseTransactionId);
    //    }

}
