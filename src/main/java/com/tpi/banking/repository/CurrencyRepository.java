package com.tpi.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.banking.entity.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {
}