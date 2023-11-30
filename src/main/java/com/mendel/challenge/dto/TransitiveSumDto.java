package com.mendel.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransitiveSumDto(
        @JsonProperty("sum") Double sum) {
}
