package com.microservices.cryptocurrencyapp;

import com.microservices.cryptocurrencyapp.domain.Cryptocurrency;
import com.microservices.cryptocurrencyapp.repository.CryptocurrencyDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CryptoDaoTestSuite {

    @Autowired
    private CryptocurrencyDao cryptoDao;

    @Test
    void saveNewCryptoAndFindByIdTest() {
        //given
        Cryptocurrency crypto = new Cryptocurrency();
        crypto.setName("Bitcoin");
        crypto.setSymbol("BTC");
        //when
        cryptoDao.save(crypto);
        var savedCrypto = cryptoDao.findBySymbol("BTC")
                .orElseThrow();
        //then
        assertEquals(crypto.getName(), savedCrypto.getName());
        assertEquals(crypto.getSymbol(), savedCrypto.getSymbol());
        //cleanUp
        cryptoDao.deleteBySymbol(savedCrypto.getSymbol());
    }
}
