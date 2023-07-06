package com.microservices.cryptocurrencyapp;

import com.microservices.cryptocurrencyapp.domain.Cryptocurrency;
import com.microservices.cryptocurrencyapp.dto.CryptocurrencyDto;
import com.microservices.cryptocurrencyapp.mapper.CryptocurrencyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CryptoMapperTestSuite {
    @Autowired
    private CryptocurrencyMapper mapper;

    @Test
    public void mapToCryptocurrencyTest() {
        // given
        CryptocurrencyDto cryptocurrencyDto = CryptocurrencyDto.builder()
                .symbol("BTC")
                .name("Bitcoin")
                .build();
        // when
        Cryptocurrency cryptocurrency = mapper.mapToCryptocurrency(cryptocurrencyDto);
        // then
        assertEquals("BTC", cryptocurrency.getSymbol());
        assertEquals("Bitcoin", cryptocurrency.getName());
    }

    @Test
    public void mapToCryptocurrencyDtoTest() {
        // given
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setSymbol("ETH");
        cryptocurrency.setName("Ethereum");
        // when
        CryptocurrencyDto cryptocurrencyDto = mapper.mapToCryptocurrencyDto(cryptocurrency);
        // then
        assertEquals("ETH", cryptocurrencyDto.getSymbol());
        assertEquals("Ethereum", cryptocurrencyDto.getName());
    }

    @Test
    public void testMapToCryptoListDto() {
        // Given
        List<Cryptocurrency> cryptos = new ArrayList<>();
        Cryptocurrency cryptocurrency1 = new Cryptocurrency();
        cryptocurrency1.setSymbol("BTC");
        cryptocurrency1.setName("Bitcoin");
        cryptos.add(cryptocurrency1);
        Cryptocurrency cryptocurrency2 = new Cryptocurrency();
        cryptocurrency2.setSymbol("ETH");
        cryptocurrency2.setName("Ethereum");
        cryptos.add(cryptocurrency2);
        // when
        List<CryptocurrencyDto> cryptoListDto = mapper.mapToCryptoListDto(cryptos);
        // given
        assertEquals(2, cryptoListDto.size());
        assertEquals("BTC", cryptoListDto.get(0).getSymbol());
        assertEquals("Bitcoin", cryptoListDto.get(0).getName());
        assertEquals("ETH", cryptoListDto.get(1).getSymbol());
        assertEquals("Ethereum", cryptoListDto.get(1).getName());
    }
}
