package com.mendel.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record TransactionDto(
        @JsonProperty("amount") Double amount,
        @JsonProperty("type") String type,
        @JsonProperty("parent_id") Long parentId) {
}
