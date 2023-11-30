package com.mendel.challenge.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Transaction {
    @NonNull
    private Long id;

    @NonNull
    private Double amount;

    @NonNull
    private String type;

    private Long parentId;
}
