package com.microservices.cryptocurrencyapp;

import com.microservices.cryptocurrencyapp.domain.Cryptocurrency;
import com.microservices.cryptocurrencyapp.exception.CryptoIsObjectOfTradingException;
import com.microservices.cryptocurrencyapp.exception.CryptocurrencyNotFoundException;
import com.microservices.cryptocurrencyapp.repository.CryptocurrencyDao;
import com.microservices.cryptocurrencyapp.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CryptoServiceTestSuite {

    @InjectMocks
    private CryptoService service;

    @Mock
    private CryptocurrencyDao cryptoDao;

    @Test
    public void testFindCryptocurrencyBySymbol() throws CryptocurrencyNotFoundException {
        // given
        String symbol = "BTC";
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setSymbol(symbol);
        when(cryptoDao.findBySymbol(symbol)).thenReturn(Optional.of(cryptocurrency));
        // when
        Cryptocurrency result = service.findCryptocurrencyBySymbol(symbol);
        // then
        assertEquals(cryptocurrency, result);
    }

    @Test
    public void testFindAll() {
        // given
        List<Cryptocurrency> expectedCryptos = new ArrayList<>();
        Cryptocurrency cryptocurrency1 = new Cryptocurrency();
        cryptocurrency1.setSymbol("BTC");
        expectedCryptos.add(cryptocurrency1);
        Cryptocurrency cryptocurrency2 = new Cryptocurrency();
        cryptocurrency2.setSymbol("ETH");
        expectedCryptos.add(cryptocurrency2);

        when(cryptoDao.findAll()).thenReturn(expectedCryptos);
        // when
        List<Cryptocurrency> result = service.findAll();
        // then
        assertEquals(expectedCryptos, result);
    }

    @Test
    public void testAdd() {
        // given
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setSymbol("BTC");
        when(cryptoDao.save(cryptocurrency)).thenReturn(cryptocurrency);
        // when
        Cryptocurrency result = service.add(cryptocurrency);
        // then
        assertEquals(cryptocurrency, result);
    }

    @Test
    public void testDeleteBySymbol() throws CryptocurrencyNotFoundException, CryptoIsObjectOfTradingException, CryptoIsObjectOfTradingException {
        // given
        String symbol = "BTC";
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setSymbol(symbol);
        when(cryptoDao.findBySymbol(symbol)).thenReturn(Optional.of(cryptocurrency));
        // when
        service.deleteBySymbol(symbol);
        // then
        verify(cryptoDao, times(1)).deleteBySymbol(symbol);
    }

    @Test
    public void testFetchListOfCryptoSymbols() {
        // Przygotowanie danych testowych
        List<Cryptocurrency> cryptos = new ArrayList<>();
        Cryptocurrency cryptocurrency1 = new Cryptocurrency();
        cryptocurrency1.setSymbol("BTC");
        cryptos.add(cryptocurrency1);
        Cryptocurrency cryptocurrency2 = new Cryptocurrency();
        cryptocurrency2.setSymbol("ETH");
        cryptos.add(cryptocurrency2);
        List<String> expectedSymbols = Arrays.asList("BTC", "ETH");

        when(cryptoDao.findAll()).thenReturn(cryptos);
        // when
        List<String> result = service.fetchListOfCryptoSymbols();
        // then
        assertEquals(expectedSymbols, result);
    }
}
