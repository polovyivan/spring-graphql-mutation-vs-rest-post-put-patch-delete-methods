package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.service.CustomerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record CustomerGraphQLQueryController(CustomerService customerService) implements GraphQLQueryResolver {

    public List<CustomerResponse> customers() {
        return customerService.getAllCustomersWithFilters();
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
