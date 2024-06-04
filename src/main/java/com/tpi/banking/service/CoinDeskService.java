package com.tpi.banking.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tpi.banking.model.coindesk.CoinDeskCurrency;
import com.tpi.banking.model.coindesk.CoinDeskResponse;
import com.tpi.banking.model.currency.CurrencyDTO;
import com.tpi.banking.model.currency.CurrencyViewModel;

@Service
public class CoinDeskService {
	private static final String COINDESK_CURRENT_PRICE_API = "https://api.coindesk.com/v1/bpi/currentprice.json";
	private final RestTemplate restTemplate;

	public CoinDeskService() {
		this.restTemplate = new RestTemplate();
	}

	public CoinDeskResponse getCoindeskCurrencies () {
		try {
			return restTemplate.getForObject(COINDESK_CURRENT_PRICE_API, CoinDeskResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch CoinDesk API", e);
		}
	}

	public CurrencyViewModel convertToCurrencyViewModel (CoinDeskResponse coinDeskResponse) {
		String updatetime = LocalDateTime.parse(coinDeskResponse.getTime().getUpdatedISO(),
			DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		CurrencyViewModel currencyViewModel = new CurrencyViewModel();
		List<CurrencyDTO> currencies = new ArrayList<>();
		Arrays.stream(coinDeskResponse.getbpi().getClass().getDeclaredFields()).peek(field -> field.setAccessible(true)).forEach(field -> {
			CoinDeskCurrency coinDeskCurrency;
			try {
				coinDeskCurrency = (CoinDeskCurrency) field.get(coinDeskResponse.getbpi());
				CurrencyDTO currencyDTO = new CurrencyDTO.Builder()
		                .code(coinDeskCurrency.getCode())
		                .name(coinDeskCurrency.getDescription())
		                .exchangeRate(coinDeskCurrency.getRateFloat())
		                .updateTime(updatetime)
		                .build();
				currencies.add(currencyDTO);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		currencyViewModel.setCurrencies(currencies);
		currencyViewModel.setUpdateTime(updatetime);
		return currencyViewModel;
	}
	
	public CurrencyViewModel getCurrentCurrencies () {
		CoinDeskResponse coinDeskResponse = getCoindeskCurrencies();
		return convertToCurrencyViewModel(coinDeskResponse);
	}
}
