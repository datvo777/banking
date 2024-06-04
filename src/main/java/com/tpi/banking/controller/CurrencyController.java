package com.tpi.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.banking.entity.CurrencyEntity;
import com.tpi.banking.model.currency.CurrencyDTO;
import com.tpi.banking.model.currency.CurrencyMapper;
import com.tpi.banking.service.CoinDeskService;
import com.tpi.banking.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
	@Autowired
	private CoinDeskService coinDeskService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CurrencyMapper currencyMapper;

	@GetMapping("/all")
	public List<CurrencyDTO> getAllCurrencies() {
		List<CurrencyEntity> currencyEntity = currencyService.getAllCurrencies();
		return currencyMapper.entitiesToDTOs(currencyEntity);
	}

	@GetMapping("/{code}")
	public ResponseEntity<CurrencyDTO> getCurrencyByCode(@PathVariable String code) {
		CurrencyEntity currencyEntity = currencyService.getCurrencyByCode(code)
				.orElseThrow(() -> new RuntimeException("Currency not found"));
		return new ResponseEntity<>(currencyMapper.entityToDTO(currencyEntity), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CurrencyDTO> addCurrency(@RequestBody CurrencyDTO currencyDTO) {
		CurrencyEntity currencyEntity = currencyService.addCurrency(currencyDTO);
		return new ResponseEntity<>(currencyMapper.entityToDTO(currencyEntity), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<CurrencyDTO> updateCurrency(@RequestBody CurrencyDTO currencyDTO) {
		CurrencyEntity currencyEntity = currencyService.updateCurrency(currencyDTO);
		return new ResponseEntity<>(currencyMapper.entityToDTO(currencyEntity), HttpStatus.OK);
	}

	@DeleteMapping("/{code}")
	public void deleteCurrency(@PathVariable String code) {
		currencyService.deleteCurrency(code);
	}
}
