package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CreatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.dto.PartiallyUpdateCustomerRequest;
import com.polovyi.ivan.dto.PartiallyUpdatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.PurchaseTransactionResponse;
import com.polovyi.ivan.dto.UpdateCustomerRequest;
import com.polovyi.ivan.dto.UpdatePurchaseTransactionRequest;
import com.polovyi.ivan.service.CustomerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Validated
@RequiredArgsConstructor
public class CustomerGraphQLMutationController implements GraphQLMutationResolver {

    private final CustomerService customerService;

    public CustomerResponse createCustomer(@Valid CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    public CustomerResponse updateCustomer(@NotNull String customerId,
            @Valid @NotNull UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateCustomer(customerId, updateCustomerRequest);
    }

    public CustomerResponse partiallyUpdateCustomer(@NotNull String customerId,
            @Valid @NotNull PartiallyUpdateCustomerRequest partiallyUpdateCustomerRequest) {
        return  customerService.partiallyUpdateCustomer(customerId, partiallyUpdateCustomerRequest);
    }

    public String deleteCustomer(@NotNull String customerId) {
        customerService.deleteCustomer(customerId);
        return customerId;
    }

    public PurchaseTransactionResponse createPurchaseTransaction(@NotNull String customerId,
            @Valid @NotNull CreatePurchaseTransactionRequest purchaseTransactionRequest) {
        return customerService.createPurchaseTransaction(customerId, purchaseTransactionRequest);
    }

    public PurchaseTransactionResponse updatePurchaseTransaction(@NotNull String customerId,
            @NotNull String purchaseTransactionId,
            @Valid @NotNull UpdatePurchaseTransactionRequest updatePurchaseTransactionRequest) {
        return customerService.updatePurchaseTransactionsById(customerId, purchaseTransactionId,
                updatePurchaseTransactionRequest);
    }

    public PurchaseTransactionResponse partiallyUpdatePurchaseTransaction(@NotNull String customerId,
            @NotNull String purchaseTransactionId,
            @Valid @NotNull PartiallyUpdatePurchaseTransactionRequest partiallyUpdatePurchaseTransactionRequest) {
        return  customerService.partiallyUpdatePurchaseTransaction(customerId, purchaseTransactionId,
                partiallyUpdatePurchaseTransactionRequest);

    }

    public String deletePurchaseTransaction(@NotNull String customerId,
            @NotNull String purchaseTransactionId) {
        customerService.deletePurchaseTransactionsById(customerId, purchaseTransactionId);
        return purchaseTransactionId;
    }

}
