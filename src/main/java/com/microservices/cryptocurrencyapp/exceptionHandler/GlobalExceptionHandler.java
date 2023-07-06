package com.microservices.cryptocurrencyapp.exceptionHandler;

import com.microservices.cryptocurrencyapp.exception.CryptoIsObjectOfTradingException;
import com.microservices.cryptocurrencyapp.exception.CryptocurrencyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CryptocurrencyNotFoundException.class)
    public ResponseEntity<Object> cryptoNotFoundExceptionHandler(CryptocurrencyNotFoundException e) {
        return new ResponseEntity<>("Cryptocurrency with given ID doest not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CryptoIsObjectOfTradingException.class)
    public ResponseEntity<Object> cryptoIsObjectOfTradingExceptionHandler(CryptoIsObjectOfTradingException e) {
        return new ResponseEntity<>("This Crypto is currently in trading. Check Trade Service first", HttpStatus.BAD_REQUEST);
    }
}
