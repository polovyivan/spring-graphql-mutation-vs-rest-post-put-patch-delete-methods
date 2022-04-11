package com.polovyi.ivan.entity;

import com.polovyi.ivan.dto.CreatePurchaseTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_transaction")
public class PurchaseTransactionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String paymentType;

    private BigDecimal amount;

    private LocalDate createdAt;

    @Column(name = "customer_id")
    private String customerId;

    public static PurchaseTransactionEntity valueOf(CreatePurchaseTransactionRequest createPurchaseTransactionRequest) {
        return builder()
                .paymentType(createPurchaseTransactionRequest.getPaymentType())
                .amount(createPurchaseTransactionRequest.getAmount())
                .createdAt(LocalDate.now())
                .build();
    }
}
