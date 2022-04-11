package com.polovyi.ivan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotNull
    private String fullName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

}
