package com.microservices.cryptocurrencyapp.service;

import com.microservices.cryptocurrencyapp.domain.Cryptocurrency;
import com.microservices.cryptocurrencyapp.exception.CryptoIsObjectOfTradingException;
import com.microservices.cryptocurrencyapp.exception.CryptocurrencyNotFoundException;
import com.microservices.cryptocurrencyapp.repository.CryptocurrencyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoService {
    private final CryptocurrencyDao cryptocurrencyDao;

    public Cryptocurrency findCryptocurrencyBySymbol(String symbol) throws CryptocurrencyNotFoundException {
        return cryptocurrencyDao.findBySymbol(symbol).orElseThrow(CryptocurrencyNotFoundException::new);
    }

    public List<Cryptocurrency> findAll() {
        return cryptocurrencyDao.findAll();
    }

    public Cryptocurrency add(Cryptocurrency cryptocurrency) {
        return cryptocurrencyDao.save(cryptocurrency);
    }

    public void deleteBySymbol(String symbol) throws CryptocurrencyNotFoundException, CryptoIsObjectOfTradingException {
        Cryptocurrency crypto = cryptocurrencyDao.findBySymbol(symbol).orElseThrow(CryptocurrencyNotFoundException::new);
        // todo check in trade service if there are trades with particular crypto
        cryptocurrencyDao.deleteBySymbol(symbol);
    }

    public List<String> fetchListOfCryptoSymbols() {
        return cryptocurrencyDao.findAll().stream()
                .map(c -> c.getSymbol())
                .collect(Collectors.toList());
    }
}
