package com.microservices.cryptocurrencyapp.controller;

import com.microservices.cryptocurrencyapp.domain.Cryptocurrency;
import com.microservices.cryptocurrencyapp.dto.CryptocurrencyDto;
import com.microservices.cryptocurrencyapp.exception.CryptoIsObjectOfTradingException;
import com.microservices.cryptocurrencyapp.exception.CryptocurrencyNotFoundException;
import com.microservices.cryptocurrencyapp.mapper.CryptocurrencyMapper;
import com.microservices.cryptocurrencyapp.service.CryptoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cryptocurrency")
@RequiredArgsConstructor
public class CryptocurrencyController {

    private final CryptoService cryptoDbCryptoService;
    private final CryptocurrencyMapper cryptoCryptocurrencyMapper;

    @GetMapping
    public ResponseEntity<List<CryptocurrencyDto>> fetchCryptocurrencyList() {
        List<Cryptocurrency> cryptos = cryptoDbCryptoService.findAll();
        return ResponseEntity.ok(cryptoCryptocurrencyMapper.mapToCryptoListDto(cryptos));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCryptocurrency(@Valid @RequestBody CryptocurrencyDto cryptocurrencydto) {
        cryptoDbCryptoService.add(cryptoCryptocurrencyMapper.mapToCryptocurrency(cryptocurrencydto));
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CryptocurrencyDto> updateCryptocurrencyData(@Valid @RequestBody CryptocurrencyDto cryptocurrencyDto) {
        var savedCrypto = cryptoDbCryptoService.add(cryptoCryptocurrencyMapper.mapToCryptocurrency(cryptocurrencyDto));
        return ResponseEntity.ok(cryptoCryptocurrencyMapper.mapToCryptocurrencyDto(savedCrypto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCryptocurrency(@RequestParam String symbol) throws CryptoIsObjectOfTradingException, CryptocurrencyNotFoundException {
        cryptoDbCryptoService.deleteBySymbol(symbol);
        return ResponseEntity.ok().build();
    }
}
