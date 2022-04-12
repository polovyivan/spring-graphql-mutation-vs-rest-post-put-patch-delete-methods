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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public record CustomerRESTController(CustomerService customerService) {

    @GetMapping(path = "/v1/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomersWithFilters();
    }

    @PostMapping(path = "/v1/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        String customerId = customerService.createCustomer(createCustomerRequest);
        response.addHeader("location", uriBuilder.path("/v1/customers/{id}").buildAndExpand(customerId).toUriString());
    }

    @PutMapping(path = "/v1/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable String customerId,
            @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        customerService.updateCustomer(customerId, updateCustomerRequest);
    }

    @PatchMapping(path = "/v1/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void partiallyUpdateCustomer(@PathVariable String customerId,
            @Valid @RequestBody PartiallyUpdateCustomerRequest partiallyUpdateCustomerRequest) {
        customerService.partiallyUpdateCustomer(customerId, partiallyUpdateCustomerRequest);
    }

    @DeleteMapping(path = "/v1/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PostMapping(path = "/v1/customers/{customerId}/purchase-transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPurchaseTransaction(@PathVariable String customerId,
            @Valid @RequestBody CreatePurchaseTransactionRequest purchaseTransactionRequest,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        String purchaseTransactionId = customerService.createPurchaseTransaction(customerId, purchaseTransactionRequest);
        response.addHeader("location", uriBuilder.path(
                        "/v1/customers/".concat(customerId).concat("/purchase-transactions/{purchaseTransactionId}"))
                .buildAndExpand(purchaseTransactionId).toUriString());

    }

    @PutMapping(path = "/v1/customers/{customerId}/purchase-transactions/{purchaseTransactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePurchaseTransaction(@PathVariable String customerId,
            @PathVariable String purchaseTransactionId, @Valid @RequestBody UpdatePurchaseTransactionRequest updatePurchaseTransactionRequest) {
         customerService.updatePurchaseTransactionsById(customerId, purchaseTransactionId, updatePurchaseTransactionRequest);
    }

    @PatchMapping(path = "/v1/customers/{customerId}/purchase-transactions/{purchaseTransactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void partiallyUpdatePurchaseTransaction(@PathVariable String customerId,
            @PathVariable String purchaseTransactionId, @Valid @RequestBody PartiallyUpdatePurchaseTransactionRequest partiallyUpdatePurchaseTransactionRequest) {
         customerService.partiallyUpdatePurchaseTransaction(customerId, purchaseTransactionId, partiallyUpdatePurchaseTransactionRequest);
    }

    @DeleteMapping(path = "/v1/customers/{customerId}/purchase-transactions/{purchaseTransactionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchaseTransaction(@PathVariable String customerId,
            @PathVariable String purchaseTransactionId) {
         customerService.deletePurchaseTransactionsById(customerId, purchaseTransactionId);
    }

}
