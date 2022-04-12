package com.polovyi.ivan.service;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CreatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.dto.PartiallyUpdateCustomerRequest;
import com.polovyi.ivan.dto.PartiallyUpdatePurchaseTransactionRequest;
import com.polovyi.ivan.dto.PurchaseTransactionResponse;
import com.polovyi.ivan.dto.UpdateCustomerRequest;
import com.polovyi.ivan.dto.UpdatePurchaseTransactionRequest;
import com.polovyi.ivan.entity.CustomerEntity;
import com.polovyi.ivan.entity.PurchaseTransactionEntity;
import com.polovyi.ivan.repository.CustomerRepository;
import com.polovyi.ivan.repository.PurchaseTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository,
                              PurchaseTransactionRepository purchaseTransactionRepository) {

    public List<CustomerResponse> getAllCustomers() {
        log.info("Getting all customers...");
        return customerRepository.findAll().stream().map(CustomerResponse::valueOf).collect(Collectors.toList());
    }

    public List<CustomerResponse> getAllCustomersWithFilters() {
        log.info("Getting all customers... ");
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponse::valueOf)
                .collect(Collectors.toList());
    }

    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        log.info("Creating a customer... ");
        CustomerEntity customer = CustomerEntity.valueOf(createCustomerRequest);
        return CustomerResponse.valueOf(customerRepository.save(customer));
    }

    public CustomerResponse updateCustomer(String customerId, @Valid UpdateCustomerRequest updateCustomerRequest) {
        log.info("Updating a customer... ");
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        customer.ifPresent(entity -> {
            entity.setFullName(updateCustomerRequest.getFullName());
            entity.setPhoneNumber(updateCustomerRequest.getPhoneNumber());
            entity.setAddress(updateCustomerRequest.getAddress());
            customerRepository.save(entity);
        });
        return customer
                .map(CustomerResponse::valueOf)
                .orElse(null);
    }

    public CustomerResponse partiallyUpdateCustomer(String customerId,
            PartiallyUpdateCustomerRequest partiallyUpdateCustomerRequest) {
        log.info("Partially updating a customer... ");

        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        customer.ifPresent(entity -> {
            entity.setPhoneNumber(partiallyUpdateCustomerRequest.getPhoneNumber());
            customerRepository.save(entity);
        });
        return customer
                .map(CustomerResponse::valueOf)
                .orElse(null);
    }

    public void deleteCustomer(String customerId) {
        log.info("Deleting a customer... ");
        customerRepository.findById(customerId).ifPresent(customerRepository::delete);
    }

    public PurchaseTransactionResponse createPurchaseTransaction(String customerId,
            CreatePurchaseTransactionRequest purchaseTransactionRequest) {
        log.info("Creating a purchase transaction... ");
        return customerRepository.findById(customerId).map(customer -> {
            PurchaseTransactionEntity purchaseTransactionEntity = PurchaseTransactionEntity.valueOf(
                    purchaseTransactionRequest);
            purchaseTransactionEntity.setCustomerId(customerId);
            return PurchaseTransactionResponse.valueOf(purchaseTransactionRepository.save(purchaseTransactionEntity));
        }).orElseThrow(RuntimeException::new);
    }

    public PurchaseTransactionResponse updatePurchaseTransactionsById(String customerId, String purchaseTransactionId,
            UpdatePurchaseTransactionRequest updatePurchaseTransactionRequest) {
        log.info("Updating a purchase transaction... ");
       return customerRepository.findById(customerId).map(customerEntity -> customerEntity.getPurchaseTransactions().stream()
                  .filter(purchaseTransaction -> purchaseTransactionId.equals(purchaseTransaction.getId()))
                  .findFirst()
                  .map(purchaseTransaction -> {
                      purchaseTransaction.setPaymentType(updatePurchaseTransactionRequest.getPaymentType());
                      purchaseTransaction.setAmount(updatePurchaseTransactionRequest.getAmount());
                      return PurchaseTransactionResponse.valueOf(purchaseTransactionRepository.save(purchaseTransaction));
                  }).orElse(null))
               .orElse(null);
    }

    public PurchaseTransactionResponse partiallyUpdatePurchaseTransaction(String customerId,
            String purchaseTransactionId,
            PartiallyUpdatePurchaseTransactionRequest partiallyUpdatePurchaseTransactionRequest) {
        log.info("Partially updating a purchase transaction... ");
       return customerRepository.findById(customerId).map(customerEntity -> customerEntity.getPurchaseTransactions().stream()
                .filter(purchaseTransaction -> purchaseTransactionId.equals(purchaseTransaction.getId()))
                .findFirst()
                .map(purchaseTransaction -> {
                    purchaseTransaction.setAmount(partiallyUpdatePurchaseTransactionRequest.getAmount());
                    return PurchaseTransactionResponse.valueOf(purchaseTransactionRepository.save(purchaseTransaction));
                }).orElse(null)).orElse(null);

    }

    public void deletePurchaseTransactionsById(String customerId, String purchaseTransactionId) {
        log.info("Deleting a purchase transaction...");
        customerRepository.findById(customerId)
                .ifPresent(customer -> {
                    customer.getPurchaseTransactions().removeIf(
                            purchaseTransaction -> purchaseTransaction.getId().equals(purchaseTransactionId));
                    customerRepository.save(customer);
                });
    }
}
