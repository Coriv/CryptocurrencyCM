package com.microservices.cryptocurrencyapp.repository;

import com.microservices.cryptocurrencyapp.domain.Cryptocurrency;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CryptocurrencyDao extends CrudRepository<Cryptocurrency, String> {
}