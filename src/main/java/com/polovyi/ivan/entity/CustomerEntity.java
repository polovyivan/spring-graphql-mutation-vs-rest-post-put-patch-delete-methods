package com.polovyi.ivan.entity;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private LocalDate createdAt;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<PurchaseTransactionEntity> purchaseTransactions;

    public static CustomerEntity valueOf(CreateCustomerRequest createCustomerRequest) {
        return builder()
                .fullName(createCustomerRequest.getFullName())
                .phoneNumber(createCustomerRequest.getPhoneNumber())
                .address(createCustomerRequest.getAddress())
                .createdAt(LocalDate.now())
                .build();
    }
}