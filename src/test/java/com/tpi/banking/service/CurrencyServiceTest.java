package com.tpi.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tpi.banking.BaseTest;
import com.tpi.banking.entity.CurrencyEntity;
import com.tpi.banking.model.currency.CurrencyDTO;

class CurrencyServiceTest extends BaseTest {
	@Autowired
	CurrencyService currencyService;

	@Test
	void testAddCurrency() {
		CurrencyEntity currencyEntity = currencyService.addCurrency(mockCurrencyDTO);
		assertEquals(currencyEntity.getCode(), mockCurrencyDTO.getCode());
	}

	@Test
	void testAddExistedCurrency() {
		testAddCurrency();
		assertThrows(RuntimeException.class, () -> {
			currencyService.addCurrency(mockCurrencyDTO);
		});
	}
	
	@Test
	void testAddMultipleCurrencies() {
		CurrencyDTO mockCurrencyDTO2 = new CurrencyDTO.Builder()
                .code("AUD")
                .name("Australian Dollar")
                .build();

		List<CurrencyDTO> currenciesDTO = Arrays.asList(mockCurrencyDTO, mockCurrencyDTO2);
		currencyService.addMultipleCurrencies(currenciesDTO);

		Optional<CurrencyEntity> optional = currencyService.getCurrencyByCode(mockCurrencyDTO.getCode());
		assertTrue(optional.isPresent());
		optional = currencyService.getCurrencyByCode(mockCurrencyDTO2.getCode());
		assertTrue(optional.isPresent());
	}

	@Test
	void testGetAllCurrencies() {
		testAddCurrency();
		List<CurrencyEntity> currencyEntities = currencyService.getAllCurrencies();
		assertTrue(currencyEntities.size() > 0);
		assertEquals(1, currencyEntities.stream().filter(c -> c.getCode().equals(mockCurrencyDTO.getCode())).count());
	}

	@Test
	void testGetCurrencyByCode() {
		testAddCurrency();
		Optional<CurrencyEntity> optional = currencyService.getCurrencyByCode(mockCurrencyDTO.getCode());
		assertTrue(optional.isPresent());
	}
	
	@Test
	void testUpdateCurrency() {
		testAddCurrency();
		String updatedName = "VN Dong";
		mockCurrencyDTO.setName(updatedName);
		
		currencyService.updateCurrency(mockCurrencyDTO);
		Optional<CurrencyEntity> optional = currencyService.getCurrencyByCode(mockCurrencyDTO.getCode());
		assertTrue(optional.isPresent());
		assertEquals(optional.get().getName(), updatedName);
	}
	
	@Test
	void testUpdateNonExistedCurrency() {
		testAddCurrency();
		assertThrows(RuntimeException.class, () -> {
			mockCurrencyDTO.setCode("ABC");
			currencyService.updateCurrency(mockCurrencyDTO);
		});
	}
	
	@Test
	void testUpdateMultipleCurrencies() {
		testAddMultipleCurrencies();
		String updatedName = "Updated Name";
		CurrencyDTO mockCurrencyDTO2 = new CurrencyDTO.Builder()
                .code("AUD")
                .name(updatedName)
                .build();
		mockCurrencyDTO.setName(updatedName);
		List<CurrencyDTO> currenciesDTO = Arrays.asList(mockCurrencyDTO, mockCurrencyDTO2);
		currencyService.updateMultipleCurrencies(currenciesDTO);
		
	
		Optional<CurrencyEntity> optional = currencyService.getCurrencyByCode(mockCurrencyDTO.getCode());
		assertTrue(optional.isPresent());
		assertEquals(optional.get().getName(), updatedName);
		
		
		optional = currencyService.getCurrencyByCode(mockCurrencyDTO2.getCode());
		assertTrue(optional.isPresent());
		assertEquals(optional.get().getName(), updatedName);
	}
	
	@Test
	void testDeleteCurrency() {
		testAddCurrency();
		currencyService.deleteCurrency(mockCurrencyDTO.getCode());
		Optional<CurrencyEntity> optional = currencyService.getCurrencyByCode(mockCurrencyDTO.getCode());
		assertFalse(optional.isPresent());
	}
}
