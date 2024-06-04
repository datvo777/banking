package com.tpi.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.banking.entity.CurrencyEntity;
import com.tpi.banking.model.currency.CurrencyDTO;
import com.tpi.banking.model.currency.CurrencyMapper;
import com.tpi.banking.repository.CurrencyRepository;

@Service
public class CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private CurrencyMapper currencyMapper;

	public List<CurrencyEntity> getAllCurrencies() {
		return currencyRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
	}

	public Optional<CurrencyEntity> getCurrencyByCode(String code) {
		return currencyRepository.findById(code);
	}

	public boolean validateAddCurrency(CurrencyDTO currencyDTO) {
		if (null == currencyDTO.getCode()) {
			throw new RuntimeException("Currency code cannot be null");
		}
		if (checkExistCode(currencyDTO.getCode())) {
			throw new RuntimeException("This currency code already exists!");
		}
		return true;
	}

	public boolean validateUpdateCurrency(CurrencyDTO currencyDTO) {
		if (null == currencyDTO.getCode()) {
			throw new RuntimeException("Currency code cannot be null");
		}
		return true;
	}

	public CurrencyEntity addCurrency(CurrencyDTO currencyDTO) {
		CurrencyEntity currencyEntity = currencyMapper.dtoToEntity(currencyDTO);
		if (validateAddCurrency(currencyDTO)) {
			return currencyRepository.save(currencyEntity);
		}
		return currencyEntity;
	}

	public List<CurrencyEntity> addMultipleCurrencies(List<CurrencyDTO> currencyDTO) {
		currencyDTO.stream().forEach(this::validateAddCurrency);
		List<CurrencyEntity> currencyEntities = currencyMapper.dtostoEntities(currencyDTO);
		return currencyRepository.saveAll(currencyEntities);
	}

	public List<CurrencyEntity> updateMultipleCurrencies(List<CurrencyDTO> currencyDTO) {
		currencyDTO.stream().forEach(this::validateUpdateCurrency);
		List<CurrencyEntity> currencyEntities = currencyMapper.dtostoEntities(currencyDTO);
		return currencyRepository.saveAll(currencyEntities);
	}

	@Transactional
	public CurrencyEntity updateCurrency(CurrencyDTO currencyDTO) {
		currencyRepository.findById(currencyDTO.getCode())
				.orElseThrow(() -> new RuntimeException("Currency not found"));
		return currencyRepository.save(currencyMapper.dtoToEntity(currencyDTO));
	}

	public void deleteCurrency(String code) {
		if (checkExistCode(code)) {
			currencyRepository.deleteById(code);
		} else {
			throw new RuntimeException("Currency not found");
		}
	}

	private boolean checkExistCode(String code) {
		Optional<CurrencyEntity> optional = this.getCurrencyByCode(code);
		return optional.isPresent();
	}
}
