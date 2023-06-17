package com.microservices.cryptocurrencyapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptocurrencyDto {
    private final String symbol;
    private final String name;
}
