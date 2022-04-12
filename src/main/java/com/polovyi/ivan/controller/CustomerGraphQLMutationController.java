package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CreatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.PartiallyUpdateCustomerRequest;
import com.polovyi.ivan.dto.PartiallyUpdatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.UpdateCustomerRequest;
import com.polovyi.ivan.dto.UpdatePurchaseTransactionRequest;
import com.polovyi.ivan.service.CustomerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Validated
@RequiredArgsConstructor
public class CustomerGraphQLMutationController implements GraphQLMutationResolver {

    private final CustomerService customerService;

    public String createCustomer(@Valid CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    public String updateCustomer(@NotNull String customerId,
            @Valid @NotNull UpdateCustomerRequest updateCustomerRequest) {
        customerService.updateCustomer(customerId, updateCustomerRequest);
        return customerId;
    }

    public String partiallyUpdateCustomer(@NotNull String customerId,
            @Valid @NotNull PartiallyUpdateCustomerRequest partiallyUpdateCustomerRequest) {
        customerService.partiallyUpdateCustomer(customerId, partiallyUpdateCustomerRequest);
        return customerId;
    }

    public String deleteCustomer(@NotNull String customerId) {
        customerService.deleteCustomer(customerId);
        return customerId;
    }

    public String createPurchaseTransaction(@NotNull String customerId,
            @Valid @NotNull CreatePurchaseTransactionRequest purchaseTransactionRequest) {
        return customerService.createPurchaseTransaction(customerId, purchaseTransactionRequest);
    }

    public String updatePurchaseTransaction(@NotNull String customerId,
            @NotNull String purchaseTransactionId, @Valid @NotNull UpdatePurchaseTransactionRequest updatePurchaseTransactionRequest) {
        customerService.updatePurchaseTransactionsById(customerId, purchaseTransactionId, updatePurchaseTransactionRequest);
        return purchaseTransactionId;
    }

    public String partiallyUpdatePurchaseTransaction(@NotNull  String customerId,
            @NotNull String purchaseTransactionId, @Valid @NotNull PartiallyUpdatePurchaseTransactionRequest partiallyUpdatePurchaseTransactionRequest) {
        customerService.partiallyUpdatePurchaseTransaction(customerId, purchaseTransactionId, partiallyUpdatePurchaseTransactionRequest);
        return purchaseTransactionId;
    }

    public String  deletePurchaseTransaction( @NotNull String customerId,
            @NotNull String purchaseTransactionId) {
        customerService.deletePurchaseTransactionsById(customerId, purchaseTransactionId);
        return purchaseTransactionId;
    }

}
