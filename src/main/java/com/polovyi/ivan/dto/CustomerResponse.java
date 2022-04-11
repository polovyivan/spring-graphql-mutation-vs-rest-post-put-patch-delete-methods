package com.polovyi.ivan.dto;

import com.polovyi.ivan.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private LocalDate createdAt;

    private List<PurchaseTransactionResponse> purchaseTransactions;

    public static CustomerResponse valueOf(CustomerEntity customer) {
        List<PurchaseTransactionResponse> purchaseTransactionResponses = customer.getPurchaseTransactions().stream()
                .map(PurchaseTransactionResponse::valueOf).collect(Collectors.toList());
        return builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .purchaseTransactions(purchaseTransactionResponses)
                .build();
    }
}
