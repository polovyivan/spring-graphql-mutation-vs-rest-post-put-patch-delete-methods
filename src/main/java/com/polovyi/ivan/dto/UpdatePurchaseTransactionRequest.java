package com.polovyi.ivan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePurchaseTransactionRequest {

    @NotNull
    private String paymentType;

    @NotNull
    private BigDecimal amount;

}
