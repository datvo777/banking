package com.tpi.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.tpi.banking.model.coindesk.CoinDeskBpi;
import com.tpi.banking.model.coindesk.CoinDeskCurrency;
import com.tpi.banking.model.coindesk.CoinDeskTime;
import com.tpi.banking.model.coindesk.CoinDeskResponse;
import com.tpi.banking.model.currency.CurrencyViewModel;

class CoinDeskServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CoinDeskService coinDeskService;

	private CoinDeskResponse mockCoinDeskResponse;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		CoinDeskCurrency mockUSDCurrency = new CoinDeskCurrency();
		mockUSDCurrency.setCode("USD");
		mockUSDCurrency.setDescription("United States Dollar");
		mockUSDCurrency.setRateFloat(50000.0f);

		CoinDeskCurrency mockEURCurrency = new CoinDeskCurrency();
		mockEURCurrency.setCode("EUR");
		mockEURCurrency.setDescription("Euro Dollar");
		mockEURCurrency.setRateFloat(60000.0f);
		
		CoinDeskCurrency mockGBPCurrency = new CoinDeskCurrency();
		mockGBPCurrency.setCode("GBP");
		mockGBPCurrency.setDescription("British Pound");
		mockGBPCurrency.setRateFloat(70000.0f);
		
		CoinDeskTime mockTime = new CoinDeskTime();
		mockTime.setUpdatedISO("2024-06-01T12:00:00Z");

		mockCoinDeskResponse = new CoinDeskResponse();
		mockCoinDeskResponse.setTime(mockTime);
		CoinDeskBpi bpi = new CoinDeskBpi();
		bpi.setUsd(mockUSDCurrency);
		bpi.setGbp(mockGBPCurrency);
		bpi.setEur(mockEURCurrency);
		mockCoinDeskResponse.setbpi(bpi);
	}

	@Test
	void testGetCoindeskCurrencies() {
		 when(restTemplate.getForObject(any(String.class), any(Class.class)))
         	.thenReturn(mockCoinDeskResponse);

		 CoinDeskResponse response = coinDeskService.getCoindeskCurrencies();
		 assertNotNull(response);
		 assertEquals("USD", response.getbpi().getUsd().getCode());
	}

	@Test
	void testConvertToCurrencyViewModel() {
		CurrencyViewModel viewModel = coinDeskService.convertToCurrencyViewModel(mockCoinDeskResponse);

		assertNotNull(viewModel);
		assertEquals(3, viewModel.getCurrencies().size());
		assertEquals("USD", viewModel.getCurrencies().get(0).getCode());
		assertEquals("United States Dollar", viewModel.getCurrencies().get(0).getName());
		assertEquals(50000.0f, viewModel.getCurrencies().get(0).getExchangeRate());
		assertEquals("2024/06/01 12:00:00", viewModel.getCurrencies().get(0).getUpdateTime());
	}
}
