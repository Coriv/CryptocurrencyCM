package com.microservices.cryptocurrencyapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = @Index(columnList = "symbol"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cryptocurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private String symbol;

    @NotNull
    @Column(unique = true)
    private String name;
}
